package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.config.JWTUtil;
import ch.collen.preterbackendserver.db.UserRepository;
import ch.collen.preterbackendserver.web.dto.AuthResponseDto;
import ch.collen.preterbackendserver.web.dto.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class SecurityResource {
    private final JWTUtil jwtUtil;
    private final Pbkdf2PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public SecurityResource(JWTUtil jwtUtil, Pbkdf2PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponseDto>> login(@RequestBody LoginDto loginDto) {
        return userRepository.findAllByEmail(loginDto.getEmail())
                .filter(userDetails -> passwordEncoder.encode(loginDto.getPassword()).equals(userDetails.password()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponseDto(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));

    }

}
