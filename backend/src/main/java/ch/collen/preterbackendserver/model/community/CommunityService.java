package ch.collen.preterbackendserver.model.community;

import ch.collen.preterbackendserver.model.item.Item;
import ch.collen.preterbackendserver.model.item.ItemLinkedToCommunityRepository;

public class CommunityService {

    private final ItemLinkedToCommunityRepository itemLinkedToCommunityRepository;

    public CommunityService(ItemLinkedToCommunityRepository itemLinkedToCommunityRepository) {
        this.itemLinkedToCommunityRepository = itemLinkedToCommunityRepository;
    }

    public boolean hasArticle(Item itemToBorrow) {
        return itemLinkedToCommunityRepository.contains(itemToBorrow);
    }
}
