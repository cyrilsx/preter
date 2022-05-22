package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.db.PersonRepository;
import ch.collen.preterbackendserver.db.document.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;

@Testcontainers
@WebFluxTest(PersonResource.class)
class PersonResourceIT {
    @MockBean
    PersonRepository repository;

    @Autowired
    private WebTestClient webClient;

    public static final Person PERSON = Person.builder().id(1L).email("cyril@tets.ch").username("user").shortUrl("cycy").build();

    @BeforeEach
    void setUpData() {
        BDDMockito.given(repository.findAllByShortUrl(ArgumentMatchers.anyString())).willReturn(Mono.just(PERSON));
    }

    @Test
    void testFindByShortUrl() {
        webClient.get()
                .uri("/api/person/cyril")
                .header("Authorization", AuthenticationHelper.basicTestAuth())
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Person.class)
                .isEqualTo(PERSON);

        Mockito.verify(repository, times(1)).findAllByShortUrl("cyril");

    }
}