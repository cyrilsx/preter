package ch.collen.preterbackendserver.db.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Migrate to class for now, as record is not supported by Spring Data MongoDB
 *
 * Represents a recommandation for a user
 */
 @Document("recommandations")
 public class Recommandation {

     @Id
     private RecommandationId recommandationId;
     private String description;

     public Recommandation() {
     }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
