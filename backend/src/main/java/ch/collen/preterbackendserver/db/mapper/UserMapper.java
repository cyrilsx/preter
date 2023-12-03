package ch.collen.preterbackendserver.db.mapper;

import ch.collen.preterbackendserver.model.User;

public class UserMapper {

    public static User fromDocument(ch.collen.preterbackendserver.db.document.User user) {
        return new User(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getShortUrl(), user.getRoles());
    }

    public static ch.collen.preterbackendserver.db.document.User fromDomain(User user) {
        return new ch.collen.preterbackendserver.db.document.User(user.id(), user.username(), user.password(), user.email(), user.roles());
    }
}
