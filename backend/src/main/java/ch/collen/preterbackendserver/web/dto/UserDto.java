package ch.collen.preterbackendserver.web.dto;


import java.util.Objects;

public class UserDto {
    private String id;
    private String email;

    private String username;
    private String shortUrl;
    private String image;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(email, userDto.email) && Objects.equals(username, userDto.username) && Objects.equals(shortUrl, userDto.shortUrl) && Objects.equals(image, userDto.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, shortUrl, image);
    }
}
