package ch.collen.preterbackendserver.db;

import ch.collen.preterbackendserver.db.document.Article;
import ch.collen.preterbackendserver.db.document.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ArticleRepository extends ReactiveMongoRepository<Article, Long> {

    Flux<Article> findArticleByCategory(Category category, Pageable pageable);

    Flux<Article> findArticleByOwner(String owner, Pageable pageable);

}

