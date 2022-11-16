package app.adoneadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AdoneAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdoneAdminApplication.class, args);
    }

}
