package org.bl.api.signature;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author beckl
 */
public class APISignature {
    public static final String SECRET_KEY = "rGoy+beNph0qGBLj6Aqoydj6SGA=";

    public static void main(String[] args) {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        byte[] message = "foobar".getBytes(StandardCharsets.UTF_8);
        HMAC hmac = new HMAC(decodedKey, HMAC.Hash.SHA256);
        System.out.println(new String(hmac.digest(message)));
    }
}
