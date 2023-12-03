package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.config.JWTUtil;
import ch.collen.preterbackendserver.db.UserRepository;
import ch.collen.preterbackendserver.db.document.Role;
import ch.collen.preterbackendserver.db.document.User;
import ch.collen.preterbackendserver.db.mapper.UserMapper;
import ch.collen.preterbackendserver.web.dto.AuthResponseDto;
import ch.collen.preterbackendserver.web.dto.LoginDto;
import ch.collen.preterbackendserver.web.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
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
                .filter(userDetails -> passwordEncoder.matches(loginDto.getPassword(),userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponseDto(jwtUtil.generateToken(UserMapper.fromDocument(userDetails)))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<UserDto>> register(@RequestBody UserDto userDto) {
        return userRepository.save(new User(
                null,
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getEmail(),
                Set.of(Role.USER))
        ).map(user -> ResponseEntity.ok(userDto));
    }

}
