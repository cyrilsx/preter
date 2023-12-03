package ch.collen.preterbackendserver.db;

import ch.collen.preterbackendserver.PreterBackendServerApplication;
import ch.collen.preterbackendserver.db.mapper.UserMapper;
import ch.collen.preterbackendserver.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PreterBackendServerApplication.class)
class UserRepositoryIT {

    private static final User USER = new User("1", "user", "", "cyril@tets.ch", "cycy", Collections.emptySet());
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri",
                () -> mongoDBContainer.getReplicaSetUrl());
    }

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.save(UserMapper.fromDomain(USER)).block();
    }

    @Test
    void findAllByEmail_none() {
        assertThat(userRepository.findAllByEmail("unknown@test.ch").block(Duration.of(5, ChronoUnit.SECONDS))).isNull();
    }

    @Test
    void findAllByEmail_found() {
        assertThat(userRepository.findAllByEmail(USER.email()).block(Duration.of(5, ChronoUnit.SECONDS))).isEqualTo(USER);
    }

    @Test
    void size() {
        assertThat(userRepository.count().block(Duration.of(5, ChronoUnit.SECONDS))).isEqualTo(1L);
    }

    @Test
    void findAllByShortUrl_found() {
        assertThat(userRepository.findAllByShortUrl(USER.shortUrl()).block(Duration.of(5, ChronoUnit.SECONDS))).isEqualTo(USER);
    }
}