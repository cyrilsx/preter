package ch.collen.preterbackendserver.e2e;

import ch.collen.preterbackendserver.config.JWTUtil;
import ch.collen.preterbackendserver.db.UserRepository;
import ch.collen.preterbackendserver.db.mapper.UserMapper;
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
import org.springframework.http.*;
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

    @Autowired
    private JWTUtil jwtUtil;

    private static final User USER = new User("1", "user", "password", "cyril@tets.ch", "cycy", Collections.emptySet());

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
    private String token;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri",
                () -> mongoDBContainer.getReplicaSetUrl());
    }

    @BeforeEach
    void setUpData() {
        userRepository.deleteAll().block();
        userRepository.save(UserMapper.fromDomain(USER)).block();
        token = jwtUtil.generateToken(USER);
    }

    @Test
    void testFindByShortUrl() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        ResponseEntity<UserDto> responseEntity = restTemplate
                .exchange("http://localhost:" + port + "/api/users/" + USER.shortUrl(),
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaders),
                        UserDto.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(UserResource.map(USER));
    }
}