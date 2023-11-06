package ch.collen.preterbackendserver.web.mapper;

import ch.collen.preterbackendserver.model.item.Item;
import ch.collen.preterbackendserver.web.dto.ItemDto;

public class ItemMapper {

    public ItemDto toItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
//        itemDto.setId(item.id());
        itemDto.setName(item.name());
        itemDto.setCategory(item.category());
        itemDto.setOwner(item.owner().username());
        itemDto.setShortUrl(item.shortUrl());
//        itemDto.setUrl(item.url());
        return itemDto;
    }
}
