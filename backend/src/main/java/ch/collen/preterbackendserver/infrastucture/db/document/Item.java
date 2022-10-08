package ch.collen.preterbackendserver.infrastucture.db.document;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("items")
@Data
@Builder
@Jacksonized
public class Item {
    @Id
    private Long id;
    private String name;

    private String description;
    private String shortUrl;
    private Category category;
    private List<String> imageUrl;
    private String owner;

}
