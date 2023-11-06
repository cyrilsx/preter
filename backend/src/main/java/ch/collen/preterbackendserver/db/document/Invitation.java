package ch.collen.preterbackendserver.db.document;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("invitations")
public class Invitation {

    @Id
    private String id;

    private String email;

    private String randomInvitationId;

    @CreatedDate
    private LocalDateTime createdAt;

    public Invitation() {
    }

    public Invitation(String email, String randomInvitationId) {
        this.email = email;
        this.randomInvitationId = randomInvitationId;
    }

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

    public String getRandomInvitationId() {
        return randomInvitationId;
    }

    public void setRandomInvitationId(String randomInvitationId) {
        this.randomInvitationId = randomInvitationId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
