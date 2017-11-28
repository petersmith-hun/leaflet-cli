package hu.psprog.leaflet.cli.service;

/**
 * @author Peter Smith
 */
public interface JWTService {

    String generateToken(String service, int expiration);
}
