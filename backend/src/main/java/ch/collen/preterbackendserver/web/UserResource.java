package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.infrastucture.db.UserRepository;
import ch.collen.preterbackendserver.web.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Mono<String> findByShortUrl() {
        return Mono.just("Hello");
    }

    @GetMapping("/{short-url}")
    public Mono<UserDto> findByShortUrl(@PathVariable("short-url") String shortUrl) {
        return userRepository.findAllByShortUrl(shortUrl).map(user -> mapper.map(user, UserDto.class));
    }


}
