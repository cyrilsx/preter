package ch.collen.preterbackendserver.infrastucture.db;

import ch.collen.preterbackendserver.infrastucture.db.document.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, Long> {
    Mono<User> findAllByEmail(String value);
    Mono<User> findAllByShortUrl(String value);
}
