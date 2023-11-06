package ch.collen.preterbackendserver.web.dto;

public class InvitationRequestId {

    private String invitationRequestId;
    private UserDto userDto;

    public InvitationRequestId() {
    }

    public InvitationRequestId(String invitationRequestId) {
        this.invitationRequestId = invitationRequestId;
    }

    public String getInvitationRequestId() {
        return invitationRequestId;
    }

    public void setInvitationRequestId(String invitationRequestId) {
        this.invitationRequestId = invitationRequestId;
    }

    @Override
    public String toString() {
        return "invitationRequestId -> " + this.invitationRequestId;
    }
}
