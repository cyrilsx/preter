package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.infrastucture.db.ItemRepository;
import ch.collen.preterbackendserver.infrastucture.db.document.Category;
import ch.collen.preterbackendserver.web.dto.ItemDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/item")
public class ItemResource {


    private final ModelMapper mapper = new ModelMapper();
    private final ItemRepository itemRepository;

    public ItemResource(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @GetMapping
    public Flux<ItemDto> findAll() {
        return itemRepository.findAll().map(item -> mapper.map(item, ItemDto.class));
    }

    @GetMapping("/{category}")
    public Flux<ItemDto> findByCategory(@PathVariable("category") String category) {
        return itemRepository.findArticleByCategory(Category.valueOf(category), Pageable.ofSize(50).first()).map(item -> mapper.map(item, ItemDto.class));
    }

    @GetMapping("/from/{owner}")
    public Flux<ItemDto> findByOwner(@PathVariable("owner") String owner) {
        return itemRepository.findArticleByOwner(owner, Pageable.ofSize(50).first()).map(item -> mapper.map(item, ItemDto.class));
    }
}
