package ch.collen.preterbackendserver.db.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

// migrate to class for now, as record is not supported by Spring Data MongoDB
@Document("users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;

    private String shortUrl;
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, String email, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public User(String id, String username, String password, String email, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() { return password; }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
