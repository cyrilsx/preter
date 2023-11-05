package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.config.JWTUtil;
import ch.collen.preterbackendserver.db.UserRepository;
import ch.collen.preterbackendserver.web.dto.AuthDto;
import ch.collen.preterbackendserver.web.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationResource {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final Pbkdf2PasswordEncoder passwordEncoder;

    public AuthenticationResource(UserRepository userRepository, JWTUtil jwtUtil, Pbkdf2PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthDto>> login(@RequestBody UserDto ar) {
        return userRepository.findAllByEmail(ar.getEmail())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.password()))
                .map(userDetails -> ResponseEntity.ok(new AuthDto(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

}
