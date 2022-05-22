package ch.collen.preterbackendserver.db;

import ch.collen.preterbackendserver.PreterBackendServerApplication;
import ch.collen.preterbackendserver.db.document.Person;
import ch.collen.preterbackendserver.web.PersonResource;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(classes = PreterBackendServerApplication.class/*, initializers = PersonRepositoryIT.Initializer.class*/)
class PersonRepositoryIT {

    public static final Person PERSON = Person.builder().id(1L).email("cyril@tets.ch").username("user").shortUrl("cycy").build();
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
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personRepository.deleteAll();
        personRepository.save(PERSON).block();
    }

    @Test
    void findAllByEmail_none() {
        assertThat(personRepository.findAllByEmail("unknown@test.ch").block(Duration.of(5, ChronoUnit.SECONDS))).isNull();
    }

    @Test
    void findAllByEmail_found() {
        assertThat(personRepository.findAllByEmail(PERSON.getEmail()).block(Duration.of(5, ChronoUnit.SECONDS))).isEqualTo(PERSON);
    }

    @Test
    void size() {
        assertThat(personRepository.count().block(Duration.of(5, ChronoUnit.SECONDS))).isEqualTo(1L);
    }

    @Test
    void findAllByShortUrl_found() {
        assertThat(personRepository.findAllByShortUrl(PERSON.getShortUrl()).block(Duration.of(5, ChronoUnit.SECONDS))).isEqualTo(PERSON);
    }
}