package ch.collen.preterbackendserver.web;

import org.bson.internal.Base64;

import java.nio.charset.StandardCharsets;

public class AuthenticationHelper {

    static String basicTestAuth() {
        return String.format("Basic %s", Base64.encode("test:password".getBytes(StandardCharsets.UTF_8)));
    }
}
