package pl.bajtas.whoshouldcall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"pl.bajtas.whoshouldcall.config"})
public class WhoShouldCallApplication {
    public static void main(String[] args) {
        SpringApplication.run(WhoShouldCallApplication.class, args);
    }
}
