package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.db.ItemRepository;
import ch.collen.preterbackendserver.db.document.Item;
import ch.collen.preterbackendserver.web.dto.ItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebFluxTest(ItemResource.class)
class ItemResourceIT {
    @MockBean
    ItemRepository repository;


    @Autowired
    private WebTestClient webClient;


    @BeforeEach
    void setUpData() {
        BDDMockito.given(repository.findArticleByOwner(ArgumentMatchers.anyString(), any())).willReturn(Flux.just(new Item()));
    }

    @Test
    void testFindByShortUrl() {
        webClient.get()
                .uri("/api/users/cyril")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(ItemDto.class)
                .isEqualTo(null);

        verify(repository, times(1)).findArticleByOwner("cyril", null);

    }
}