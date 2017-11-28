package hu.psprog.leaflet.cli.service;

/**
 * Service for JWT token related operations.
 *
 * @author Peter Smith
 */
public interface JWTService {

    /**
     * Generates a SERVICE-role for the given service.
     *
     * @param service service name to generate token for
     * @param expiration expiration time in hours
     * @return generated token as String
     */
    String generateToken(String service, int expiration);
}
