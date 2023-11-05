package ch.collen.preterbackendserver.db.document;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * migrate to class for now, as record is not supported by Spring Data MongoDB
 *
 */
@Document("user_communities")
public class UserCommunity {
    private String userId;
    private String communityId;

    public UserCommunity() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    @Override
    public String toString() {
        return "UserCommunity{" +
                "userId='" + userId + '\'' +
                ", communityId='" + communityId + '\'' +
                '}';
    }
}
