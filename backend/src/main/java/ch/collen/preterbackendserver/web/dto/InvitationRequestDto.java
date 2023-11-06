package ch.collen.preterbackendserver.web.dto;

public class InvitationRequestDto {

    private String email;

    public InvitationRequestDto() {
    }
    public InvitationRequestDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "InvitationRequestDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
