package ch.collen.preterbackendserver.db;

import ch.collen.preterbackendserver.PreterBackendServerApplication;
import ch.collen.preterbackendserver.infrastucture.db.UserRepository;
import ch.collen.preterbackendserver.infrastucture.db.document.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(classes = PreterBackendServerApplication.class/*, initializers = PersonRepositoryIT.Initializer.class*/)
class UserRepositoryIT {

    public static final User USER = User.builder().id(1L).email("cyril@tets.ch").username("user").shortUrl("cycy").build();
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri",
                () -> mongoDBContainer.getReplicaSetUrl());
    }
    /*
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    String.format("spring.data.mongodb.uri: %s", mongoDBContainer.getReplicaSetUrl())
            ).applyTo(configurableApplicationContext);
        }
    }*/

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.save(USER).block();
    }

    @Test
    void findAllByEmail_none() {
        assertThat(userRepository.findAllByEmail("unknown@test.ch").block(Duration.of(5, ChronoUnit.SECONDS))).isNull();
    }

    @Test
    void findAllByEmail_found() {
        assertThat(userRepository.findAllByEmail(USER.getEmail()).block(Duration.of(5, ChronoUnit.SECONDS))).isEqualTo(USER);
    }

    @Test
    void size() {
        assertThat(userRepository.count().block(Duration.of(5, ChronoUnit.SECONDS))).isEqualTo(1L);
    }

    @Test
    void findAllByShortUrl_found() {
        assertThat(userRepository.findAllByShortUrl(USER.getShortUrl()).block(Duration.of(5, ChronoUnit.SECONDS))).isEqualTo(USER);
    }
}