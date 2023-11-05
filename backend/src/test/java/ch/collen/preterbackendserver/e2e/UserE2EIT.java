package ch.collen.preterbackendserver.e2e;

import ch.collen.preterbackendserver.db.UserRepository;
import ch.collen.preterbackendserver.model.User;
import ch.collen.preterbackendserver.web.UserResource;
import ch.collen.preterbackendserver.web.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled
class UserE2EIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private static final User USER = new User("1", "user", "password", "cyril@tets.ch", "cycy", Collections.emptySet());

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri",
                () -> mongoDBContainer.getReplicaSetUrl());
    }

    @BeforeEach
    void setUpData() {
        userRepository.deleteAll().block();
        userRepository.save(USER).block();
    }

    @Test
    void testFindByShortUrl() {
        ResponseEntity<UserDto> responseEntity = this.restTemplate
                .withBasicAuth("cyril@tets.ch", "password")
                .exchange("http://localhost:" + port + "/api/users/" + USER.shortUrl(),
                        HttpMethod.GET,
                        null,
                        UserDto.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(UserResource.map(USER));
    }
}