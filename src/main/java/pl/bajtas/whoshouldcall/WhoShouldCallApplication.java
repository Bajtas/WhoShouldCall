package pl.bajtas.whoshouldcall;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"pl.bajtas.whoshouldcall.config"})
public class WhoShouldCallApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WhoShouldCallApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WhoShouldCallApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
