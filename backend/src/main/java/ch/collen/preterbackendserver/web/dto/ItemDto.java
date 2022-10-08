package ch.collen.preterbackendserver.web.dto;

import ch.collen.preterbackendserver.infrastucture.db.document.Category;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@Jacksonized
public class ItemDto {
    @Id
    private Long id;
    private String name;

    private String description;
    private String shortUrl;
    private Category category;
    private List<String> imageUrl;
    private String owner;


}
