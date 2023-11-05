package ch.collen.preterbackendserver.db.document;

import java.util.Objects;

public final class RecommandationId {
    private String fromUserId;
    private String toUserId;

    public RecommandationId() {
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommandationId that = (RecommandationId) o;
        return Objects.equals(fromUserId, that.fromUserId) && Objects.equals(toUserId, that.toUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromUserId, toUserId);
    }

    @Override
    public String toString() {
        return "RecommandationId{" +
                "fromUserId='" + fromUserId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                '}';
    }
}
