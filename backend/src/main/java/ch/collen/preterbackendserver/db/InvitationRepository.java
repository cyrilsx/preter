package ch.collen.preterbackendserver.db;

import ch.collen.preterbackendserver.db.document.Invitation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface InvitationRepository extends ReactiveMongoRepository<Invitation, String> {

}
