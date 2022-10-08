package ch.collen.preterbackendserver.e2e;

import ch.collen.preterbackendserver.infrastucture.db.UserRepository;
import ch.collen.preterbackendserver.infrastucture.db.document.User;
import ch.collen.preterbackendserver.web.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

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

    public static final User USER = User.builder().id(1L).email("cyril@tets.ch").username("user").shortUrl("cycy").build();

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
                .withBasicAuth("test", "password")
                .exchange("http://localhost:" + port + "/api/users/" + USER.getShortUrl(),
                        HttpMethod.GET,
                        null,
                        UserDto.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(USER);
    }
}