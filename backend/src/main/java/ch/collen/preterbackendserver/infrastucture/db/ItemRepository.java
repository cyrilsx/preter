package ch.collen.preterbackendserver.infrastucture.db;

import ch.collen.preterbackendserver.infrastucture.db.document.Item;
import ch.collen.preterbackendserver.infrastucture.db.document.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ItemRepository extends ReactiveMongoRepository<Item, Long> {

    Flux<Item> findArticleByCategory(Category category, Pageable pageable);

    Flux<Item> findArticleByOwner(String owner, Pageable pageable);

}

