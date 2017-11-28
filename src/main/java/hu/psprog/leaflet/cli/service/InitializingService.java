package hu.psprog.leaflet.cli.service;

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
     * @param password password
     * @param language default user locale
     * @return created user's ID
     */
    Long initializeAdmin(String username, String email, String password, Locale language);
}
