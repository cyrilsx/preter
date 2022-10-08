package ch.collen.preterbackendserver.infrastucture.db.document;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Data
@Builder
@Jacksonized
public class User {

    @Id
    private Long id;
    private String email;

    private String username;
    private String shortUrl;
    private String image;

}
