package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.db.UserRepository;
import ch.collen.preterbackendserver.db.mapper.UserMapper;
import ch.collen.preterbackendserver.model.User;
import ch.collen.preterbackendserver.web.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebFluxTest(UserResource.class)
class UserResourceIT {
    @MockBean
    UserRepository repository;

    @MockBean
    ItemResource itemResource;

    @Autowired
    private WebTestClient webClient;

    private static final User USER = new User("1", "user", "", "cyril@tets.ch", "cycy", Collections.emptySet());

    @BeforeEach
    void setUpData() {
        BDDMockito.given(repository.findAllByShortUrl(ArgumentMatchers.anyString())).willReturn(Mono.just(UserMapper.fromDomain(USER)));
    }

    @Test
    void testFindByShortUrl() {
        webClient.get()
                .uri("/api/users/cyril")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(UserDto.class)
                .isEqualTo(UserResource.map(USER));

        verify(repository, times(1)).findAllByShortUrl("cyril");

    }
}