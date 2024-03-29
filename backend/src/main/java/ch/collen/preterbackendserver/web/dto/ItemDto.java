package ch.collen.preterbackendserver.web.dto;

import ch.collen.preterbackendserver.db.document.Category;
import org.springframework.data.annotation.Id;

import java.util.List;

public class ItemDto {
    @Id
    private String id;
    private String name;

    private String description;
    private String shortUrl;
    private Category category;
    private List<String> imageUrl;
    private String owner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", category=" + category +
                ", imageUrl=" + imageUrl +
                ", owner='" + owner + '\'' +
                '}';
    }
}
