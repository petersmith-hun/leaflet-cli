package hu.psprog.leaflet.cli;

import hu.psprog.leaflet.cli.config.ExternalDependencyConfiguration;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.shell.jline.PromptProvider;

/**
 * CLI entry point.
 *
 * @author Peter Smith
 */
@SpringBootApplication(exclude = ThymeleafAutoConfiguration.class)
@EnableJpaRepositories(basePackages = {
        "hu.psprog.leaflet.persistence.entity",
        "hu.psprog.leaflet.persistence.repository"
})
@Import(ExternalDependencyConfiguration.class)
public class LeafletCLIApplication {

    private static final String CLI_PROMPT = "leaflet-cli> ";

    public static void main(String[] args) {
        SpringApplication.run(LeafletCLIApplication.class, args);
    }

    @Bean
    public PromptProvider leafletCLIPromptProvider() {
        return () -> new AttributedString(CLI_PROMPT, AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));
    }
}
