package pl.bajtas.whoshouldcall.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Bajtas on 13.05.2017.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"pl.bajtas.whoshouldcall.repository"})
@ComponentScan(basePackages = {"pl.bajtas.whoshouldcall.service", "pl.bajtas.whoshouldcall.controller"})
@EntityScan(basePackages = {"pl.bajtas.whoshouldcall.model"})
public class AppConfig {

}
