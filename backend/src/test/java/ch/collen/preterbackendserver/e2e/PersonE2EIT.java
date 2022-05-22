package ch.collen.preterbackendserver.e2e;

import ch.collen.preterbackendserver.db.PersonRepository;
import ch.collen.preterbackendserver.db.document.Person;
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
class PersonE2EIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PersonRepository personRepository;

    public static final Person PERSON = Person.builder().id(1L).email("cyril@tets.ch").username("user").shortUrl("cycy").build();

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri",
                () -> mongoDBContainer.getReplicaSetUrl());
    }

    @BeforeEach
    void setUpData() {
        personRepository.deleteAll().block();
        personRepository.save(PERSON).block();
    }

    @Test
    void testFindByShortUrl() {
        ResponseEntity<Person> responseEntity = this.restTemplate
                .withBasicAuth("test", "password")
                .exchange("http://localhost:" + port + "/api/person/cyril",
                        HttpMethod.GET,
                        null,
                        Person.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(PERSON);
    }
}