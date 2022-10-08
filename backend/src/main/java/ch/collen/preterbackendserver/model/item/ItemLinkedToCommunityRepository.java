package ch.collen.preterbackendserver.model.item;

public interface ItemLinkedToCommunityRepository {
    boolean contains(Item itemToBorrow);
}
