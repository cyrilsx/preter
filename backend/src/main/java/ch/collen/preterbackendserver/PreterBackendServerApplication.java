package ch.collen.preterbackendserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class PreterBackendServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreterBackendServerApplication.class, args);
    }

}
