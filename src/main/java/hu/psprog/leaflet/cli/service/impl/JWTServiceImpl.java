package hu.psprog.leaflet.cli.service.impl;

import hu.psprog.leaflet.cli.service.JWTService;
import hu.psprog.leaflet.security.jwt.JWTComponent;
import hu.psprog.leaflet.security.jwt.model.ExtendedUserDetails;
import hu.psprog.leaflet.security.jwt.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Peter Smith
 */
@Service
public class JWTServiceImpl implements JWTService {

    private static final String SERVICE_PREFIX = "svc-";
    private static final List<GrantedAuthority> SERVICE_AUTHORITY = AuthorityUtils.createAuthorityList(Role.SERVICE.name());
    private static final long SERVICE_USER_ID = 0L;

    private JWTComponent jwtComponent;

    @Autowired
    public JWTServiceImpl(JWTComponent jwtComponent) {
        this.jwtComponent = jwtComponent;
    }

    @Override
    public String generateToken(String service, int expiration) {

        return jwtComponent
                .generateToken(buildUserDetails(service), expiration)
                .getToken();
    }

    private UserDetails buildUserDetails(String name) {
        return new ExtendedUserDetails.Builder()
                .withID(SERVICE_USER_ID)
                .withUsername(prepareServiceName(name))
                .withName(name)
                .withAuthorities(SERVICE_AUTHORITY)
                .build();
    }

    private String prepareServiceName(String name) {
        return SERVICE_PREFIX + name;
    }
}
