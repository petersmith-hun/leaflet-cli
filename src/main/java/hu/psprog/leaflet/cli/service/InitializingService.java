package hu.psprog.leaflet.cli.service;

import hu.psprog.leaflet.persistence.entity.Locale;

/**
 * @author Peter Smith
 */
public interface InitializingService {

    boolean isInitialized();

    Long initializeAdmin(String username, String email, String password, Locale language);
}
