package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.db.PersonRepository;
import ch.collen.preterbackendserver.db.document.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/person")
public class PersonResource {

    private final PersonRepository personRepository;

    public PersonResource(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Mono<String> findByShortUrl() {
        System.out.println("hello");
        return Mono.just("Hello");
    }

    @GetMapping("/{short-url}")
    public Mono<Person> findByShortUrl(@PathVariable("short-url") String shortUrl) {
        System.out.println(shortUrl);
        return personRepository.findAllByShortUrl(shortUrl);
    }


}
