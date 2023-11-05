package ch.collen.preterbackendserver.web;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;

import java.nio.charset.StandardCharsets;

public class AuthenticationHelper {

    static String basicTestAuth() {
        return String.format("Basic %s", Base64.encode(Unpooled.copiedBuffer("test:password".getBytes(StandardCharsets.UTF_8))));
    }
}
