package ch.collen.preterbackendserver.db.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("persons")
@Data
@Builder
public class Person {

    @Id
    private Long id;
    private String email;

    private String username;
    private String shortUrl;
    private String image;

}
