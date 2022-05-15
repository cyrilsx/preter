package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.db.PersonRepository;
import ch.collen.preterbackendserver.db.document.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("api/person")
public class PersonResource {

    private final PersonRepository personRepository;

    public PersonResource(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Mono<Person> findByEmail(String email) {
        return personRepository.findAllByEmail(email);
    }


}
