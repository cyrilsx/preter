package ch.collen.preterbackendserver.db;

import ch.collen.preterbackendserver.db.document.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveMongoRepository<Person, Long> {
    Mono<Person> findAllByEmail(String value);

    Mono<Person> findAllByShortUrl(String value);
}
