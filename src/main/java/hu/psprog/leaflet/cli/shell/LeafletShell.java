package hu.psprog.leaflet.cli.shell;

import hu.psprog.leaflet.cli.service.InitializingService;
import hu.psprog.leaflet.cli.service.JWTService;
import hu.psprog.leaflet.persistence.entity.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.Objects;

/**
 * Leaflet management shell.
 *
 * @author Peter Smith
 */
@ShellComponent
public class LeafletShell {

    private static final String DEFAULT_USERNAME = "Administrator";
    private static final String DEFAULT_LOCALE = "EN";
    private static final String DEFAULT_EXPIRATION_IN_HOURS = "8760";

    private JWTService jwtService;
    private InitializingService initializingService;

    @Autowired
    public LeafletShell(JWTService jwtService, InitializingService initializingService) {
        this.jwtService = jwtService;
        this.initializingService = initializingService;
    }

    /**
     * Initializes user database with the first (always administrator) user.
     *
     * @param username username of the admin user (optional, defaults to 'Administrator')
     * @param email email address (mandatory)
     * @param password password (mandatory)
     * @param locale default user locale (optional, defaults to 'EN')
     * @return operation result message
     */
    @ShellMethod(key = "init", value = "Initializes first admin user.")
    @ShellMethodAvailability("initAvailability")
    public String init(
            @ShellOption(arity = 1, defaultValue = DEFAULT_USERNAME) String username,
            @ShellOption(arity = 1) String email,
            @ShellOption(arity = 1) String password,
            @ShellOption(arity = 1, defaultValue = DEFAULT_LOCALE) Locale locale) {

        Long userID = initializingService.initializeAdmin(username, email, password, locale);
        String message;
        if (Objects.nonNull(userID)) {
            message = "Administrator user initialized with ID = " + userID;
        } else {
            message = "Failed to initialize administrator user. See stacktrace above for more information.";
        }

        return message;
    }

    /**
     * Generates a SERVICE token.
     *
     * @param service service name (mandatory)
     * @param expiration expiration in hours (optional, defaults to 8760 hours)
     * @return
     */
    @ShellMethod(key = "svc-token", value = "Generates a SERVICE user token.")
    public String generateServiceToken(
            @ShellOption(arity = 1) String service,
            @ShellOption(defaultValue = DEFAULT_EXPIRATION_IN_HOURS) int expiration) {

        return "Generated SERVICE token: " + jwtService.generateToken(service, expiration);
    }

    private Availability initAvailability() {
        return initializingService.isInitialized()
                ? Availability.unavailable("System already initialized")
                : Availability.available();
    }
}
