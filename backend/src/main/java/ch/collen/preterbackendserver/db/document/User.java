package ch.collen.preterbackendserver.db.document;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document("users")
@Data
@Builder
@Jacksonized
public class User {

    @Id
    private String email;
    private String password;
    private String randomSalt;

    private Set<Role> roles;

    private LocalDateTime creationTs;


}
