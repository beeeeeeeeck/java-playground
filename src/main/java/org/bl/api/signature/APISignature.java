package org.bl.api.signature;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
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
        System.out.println(new String(calcHmacSha256(decodedKey, message)));
        HMAC hmac = new HMAC(decodedKey, HMAC.Hash.SHA256);
        System.out.println(new String(hmac.digest(message)));
    }

    private static byte[] calcHmacSha256(byte[] secretKey, byte[] message) {
        byte[] hmacSha256 = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return hmacSha256;
    }
}
