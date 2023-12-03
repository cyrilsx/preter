package ch.collen.preterbackendserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private String tokenSecret = "M2Y2N2VjNjItZDdhMy00w6dhMGQtYWU2NC04MTMlw6dhM2EwOWUK";
    private String hashSecret = "secret";

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public String getHashSecret() {
        return hashSecret;
    }

    public void setHashSecret(String hashSecret) {
        this.hashSecret = hashSecret;
    }
}
