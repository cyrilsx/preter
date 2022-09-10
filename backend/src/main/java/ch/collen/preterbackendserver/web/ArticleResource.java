package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.db.ArticleRepository;
import ch.collen.preterbackendserver.db.document.Article;
import ch.collen.preterbackendserver.db.document.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/article")
public class ArticleResource {

    private final ArticleRepository articleRepository;


    public ArticleResource(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/{category}")
    public Flux<Article> findByCategory(@PathVariable("category") String category) {
        return articleRepository.findArticleByCategory(Category.valueOf(category), Pageable.ofSize(50).first());
    }

    @GetMapping("/from/{owner}")
    public Flux<Article> findByOwner(@PathVariable("owner") String owner) {
        return articleRepository.findArticleByOwner(owner, Pageable.ofSize(50).first());
    }
}
