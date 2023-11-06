package ch.collen.preterbackendserver.web.dto;

import java.time.Duration;

public class InvitationConfigurationDto {
    private boolean autoAccept = false;
    private Duration autoDecline = Duration.ofDays(90);


    public boolean isAutoAccept() {
        return autoAccept;
    }

    public void setAutoAccept(boolean autoAccept) {
        this.autoAccept = autoAccept;
    }

    public Duration getAutoDecline() {
        return autoDecline;
    }

    public void setAutoDecline(Duration autoDecline) {
        this.autoDecline = autoDecline;
    }
}
