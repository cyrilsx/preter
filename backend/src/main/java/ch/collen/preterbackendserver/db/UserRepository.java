package ch.collen.preterbackendserver.db;

import ch.collen.preterbackendserver.db.document.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
