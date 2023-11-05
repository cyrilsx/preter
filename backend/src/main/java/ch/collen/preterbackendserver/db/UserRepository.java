package ch.collen.preterbackendserver.db;

import ch.collen.preterbackendserver.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findAllByEmail(String value);

    Mono<User> findAllByShortUrl(String value);

}
