package ch.collen.preterbackendserver;

import ch.collen.preterbackendserver.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@Import(SecurityConfig.class)
public class PreterBackendServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreterBackendServerApplication.class, args);
    }

}
