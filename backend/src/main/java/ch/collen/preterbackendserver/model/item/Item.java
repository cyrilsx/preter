package ch.collen.preterbackendserver.model.item;

import ch.collen.preterbackendserver.db.document.Category;
import ch.collen.preterbackendserver.model.User;
import ch.collen.preterbackendserver.model.community.Community;

import java.util.Collection;
import java.util.List;

public record Item(User owner,
                   String name,
                   String description,
                   String shortUrl,
                   Category category,
                   List<String> imageUrls,
                   Collection<Community> communities) {

}
