package hu.psprog.leaflet.cli.service;

import hu.psprog.leaflet.cli.service.domain.InitResponse;
import hu.psprog.leaflet.persistence.entity.Locale;

/**
 * Service for user database initialization related operations.
 *
 * @author Peter Smith
 */
public interface InitializingService {

    /**
     * Checks if user database is already initialized.
     *
     * @return {@code true} is user database is already initialized, {@code false} otherwise
     */
    boolean isInitialized();

    /**
     * Initializes administrator user.
     *
     * @param username username
     * @param email email address
     * @param language default user locale
     * @return created user's ID and generated password as {@link InitResponse}
     */
    InitResponse initializeAdmin(String username, String email, Locale language);
}
