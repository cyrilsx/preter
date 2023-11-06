package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.config.JWTUtil;
import ch.collen.preterbackendserver.db.UserRepository;
import ch.collen.preterbackendserver.model.User;
import ch.collen.preterbackendserver.web.dto.AuthResponseDto;
import ch.collen.preterbackendserver.web.dto.LoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebFluxTest(SecurityResource.class)
class SecurityResourceIT {
    @MockBean
    UserRepository repository;

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;

    @MockBean
    private JWTUtil jwtUtil;

    private static final User USER = new User("1", "user", "password", "cyril@tets.ch", "cycy", Collections.emptySet());

    @BeforeEach
    void setUpData() {
        BDDMockito.given(repository.findAllByEmail(ArgumentMatchers.anyString())).willReturn(Mono.just(USER));
        BDDMockito.given(pbkdf2PasswordEncoder.encode(ArgumentMatchers.anyString())).willReturn(USER.password());
        BDDMockito.given(jwtUtil.generateToken(USER)).willReturn("token");
    }

    @Test
    void test_login_ok() {
        webClient.post()
                .uri("/api/login")
                .body(Mono.just(new LoginDto(USER.email(), USER.password())), LoginDto.class)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(AuthResponseDto.class)
                .isEqualTo(new AuthResponseDto("token"));

        verify(repository, times(1)).findAllByEmail(USER.email());

    }
}