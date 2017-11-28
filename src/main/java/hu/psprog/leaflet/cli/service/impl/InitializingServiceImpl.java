package hu.psprog.leaflet.cli.service.impl;

import hu.psprog.leaflet.cli.service.InitializingService;
import hu.psprog.leaflet.persistence.entity.Locale;
import hu.psprog.leaflet.security.jwt.model.Role;
import hu.psprog.leaflet.service.UserService;
import hu.psprog.leaflet.service.exception.ServiceException;
import hu.psprog.leaflet.service.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link InitializingService}.
 *
 * @author Peter Smith
 */
@Service
public class InitializingServiceImpl implements InitializingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializingServiceImpl.class);
    private static final List<GrantedAuthority> ADMIN_AUTHORITY = AuthorityUtils.createAuthorityList(Role.ADMIN.name());

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public InitializingServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isInitialized() {
        return userService.count() > 0L;
    }

    @Override
    public Long initializeAdmin(String username, String email, String password, Locale language) {

        Long userID = null;
        try {
            userID = userService.createOne(createAdminUserVO(username, email, password, language));
        } catch (ServiceException e) {
            LOGGER.error("Failed to initialize admin user", e);
        }

        return userID;
    }

    private UserVO createAdminUserVO(String username, String email, String password, Locale language) {
        return UserVO.getBuilder()
                .withUsername(username)
                .withEmail(email)
                .withPassword(passwordEncoder.encode(password))
                .withLocale(language)
                .withEnabled(true)
                .withAuthorities(ADMIN_AUTHORITY)
                .build();
    }
}
