package ch.collen.preterbackendserver.model;

import ch.collen.preterbackendserver.db.document.Role;

import java.util.Set;

public record User(String id, String username, String password, String email, String shortUrl, Set<Role> roles) {
}

