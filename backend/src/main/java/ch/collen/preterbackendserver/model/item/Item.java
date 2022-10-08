package ch.collen.preterbackendserver.model.item;

import ch.collen.preterbackendserver.model.User;
import ch.collen.preterbackendserver.model.community.Community;

import java.util.Collection;
import java.util.Collections;

public class Item {

    private final User owner;
    private final Collection<Community> community;

    public Item(User owner, Collection<Community> community) {
        this.owner = owner;
        this.community = community;
    }

    public Collection<Community> communities() {
        return Collections.unmodifiableCollection(community);
    }
}
