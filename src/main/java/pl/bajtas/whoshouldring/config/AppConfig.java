package pl.bajtas.whoshouldring.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Bajtas on 18.03.2017.
 */
@EnableJpaRepositories(basePackages = {"pl.bajtas.whoshouldring.persistence.repository"})
@ComponentScan(basePackages = {"pl.bajtas.squaremoose.api.service", "pl.bajtas.whoshouldring.controller"})
public class AppConfig extends WebMvcAutoConfiguration {

}
