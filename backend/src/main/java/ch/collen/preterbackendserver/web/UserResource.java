package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.db.UserRepository;
import ch.collen.preterbackendserver.db.document.User;
import ch.collen.preterbackendserver.web.dto.UserDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private final UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Mono<UserDto> getCurrentUser(@AuthenticationPrincipal Principal principal) {
        System.out.println(principal.getName());
        return userRepository.findAllByEmail(principal.getName()).map(UserResource::map);
    }

    @GetMapping("/{short-url}")
    public Mono<UserDto> findByShortUrl(@PathVariable("short-url") String shortUrl) {
//        return userRepository.findAllByShortUrl(shortUrl).map(UserResource::map);
        return null;
    }

    public static UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setShortUrl(user.getShortUrl());
        return userDto;
    }


}
