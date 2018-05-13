package hu.psprog.leaflet.cli.config;

import hu.psprog.leaflet.persistence.dao.UserDAO;
import hu.psprog.leaflet.security.jwt.config.JWTComponentInitializer;
import hu.psprog.leaflet.service.UserService;
import hu.psprog.leaflet.service.converter.AuthorityToRoleConverter;
import hu.psprog.leaflet.service.converter.UserToUserVOConverter;
import hu.psprog.leaflet.service.converter.UserVOToUserConverter;
import hu.psprog.leaflet.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration to build up required Leaflet service and persistence beans.
 *
 * @author Peter Smith
 */
@Configuration
@Import(value = {
        JWTComponentInitializer.class
})
@ComponentScan(basePackages = {
        "hu.psprog.leaflet.security.jwt.impl",
        "hu.psprog.leaflet.persistence.dao"
})
@EnableJpaRepositories(basePackages = {
        "hu.psprog.leaflet.persistence.entity",
        "hu.psprog.leaflet.persistence.repository"
})
@EntityScan(basePackages = {
        "hu.psprog.leaflet.persistence.entity",
        "hu.psprog.leaflet.persistence.repository"
})
public class ExternalDependencyConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserToUserVOConverter userToUserVOConverter() {
        return new UserToUserVOConverter();
    }

    @Bean
    public UserVOToUserConverter userVOToUserConverter() {
        return new UserVOToUserConverter();
    }

    @Bean
    public AuthorityToRoleConverter authorityToRoleConverter() {
        return new AuthorityToRoleConverter();
    }

    @Bean
    @Autowired
    @DependsOn("userRepository")
    public UserService userService(UserDAO userDAO, UserToUserVOConverter userToUserVOConverter, UserVOToUserConverter userVOToUserConverter,
                                   AuthorityToRoleConverter authorityToRoleConverter) {
        return new UserServiceImpl(userDAO, userToUserVOConverter, userVOToUserConverter, authorityToRoleConverter);
    }
}
