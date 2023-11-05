package ch.collen.preterbackendserver.db;

import ch.collen.preterbackendserver.db.document.Community;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CommunityRepository extends ReactiveMongoRepository<Community, String> {

}
