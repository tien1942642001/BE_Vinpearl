package dev.kienntt.demo.BE_Vinpearl.config;

import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.model.User;
import io.jsonwebtoken.*;

import java.nio.file.AccessDeniedException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtTokenProvider {
    public static String createJwtSignedHMAC(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {

        PrivateKey privateKey = getPrivateKey();

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("email", user.getEmail())
                .claim("password", user.getPassword())
                .claim("fullName", user.getFullName())
                .setSubject(user.getEmail())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
//                .setExpiration(Date.from(now.plus(1l, ChronoUnit.MINUTES)))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.DAYS)))
                .signWith(privateKey)
                .compact();

        return jwtToken;
    }

    public static String createJwtSignedHMAC1(Customer customer) throws InvalidKeySpecException, NoSuchAlgorithmException {

        PrivateKey privateKey = getPrivateKey();

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("email", customer.getEmail())
                .claim("password", customer.getPassword())
                .claim("fullName", customer.getFullName())
                .setSubject(customer.getEmail())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(20l, ChronoUnit.MINUTES)))
//                .setExpiration(Date.from(now.plus(30, ChronoUnit.DAYS)))
                .signWith(privateKey)
                .compact();

        return jwtToken;
    }

    private static PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String rsaPrivateKey = "-----BEGIN PRIVATE KEY-----" +
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCa50wiCQGzleKt" +
                "cMkwCao4l43n4tXwLmbYyS55UIAAORvjS4uFuQ2rkcyorbLeRyMEhxQbRoN0AtAL" +
                "/EDSZoCyFrUj/K83Ce9odMRHwZNaKGOUXj2nQOwBOTxofO3fMYMGk8Hm78W9+7AV" +
                "squtnLFo5avCKPbfkcW/mXJCUqooSY6P58Y3+9CQVMQJG1ZeC6ZiEXw6AYoHlYdO" +
                "tWrOBnTpEDgYFDAD3cb7Knkd3yS2RPdiUzL+5chPSWhdsX6X/IcgnyMfxhs7JiY4" +
                "uvNI4tpa5MPUOuSW/2U6oIT50BrYwVM6e5R/kQiTt71POTr+GK2vlqzxrDVdNjuD" +
                "/OMRvBHFAgMBAAECggEABT1gmKa716aKcXBgsDiMRfF9QIEIJUf4ib0IzqjS4s8L" +
                "uuayotsYrVTkLdEavUSXcjJPucCrA49XZY39zLz6MPjHxB6EK17LA6CuUmwONZne" +
                "QAxN1kmOqIrv8jisz8r/TuD/VkLsw8l1K+kfjNYBghYbKkxTvEot/K/U16cvfM3U" +
                "sYrspHJpaFmenzTvJgkc1cIYpIRXxX3uSuvc0pO0Y0xzP4WxMswvWXyvWPinaKcc" +
                "IAdMxWAH0aQ+o50YTEDzB4lxRweW1TX5VAKsy8wwebVS4hpUC5aoO1YJIgpnImUi" +
                "/+S9kKQtbWoaVa0stKVYwhU97J510iJK2BLGkSe4MQKBgQDEZBlQgXVjUXGOBb7T" +
                "FAVby4RDb00qTWF1adfAEANAsu8BcLxb2G0gmdXGGVUYEkMqniktyu+XEw/L4R4y" +
                "YEknAlKTcDwOe1aRvHeDiibUqlDUvUnZ/6qA4BWUcYijgHzlHAD2MjLO+O831i5A" +
                "Xd0Xkou95wOg1zwWPU2Wzyg71QKBgQDJ641DEqpbiRpVGlRZDOT2MYnVGlc33Z7f" +
                "YhdEx/aznQvQyXNy8u6mSoIYjs7dn15OmFY/uBq1IJJttH4DejeOC8eKBlBsm8fT" +
                "rWqxYXyZMQryoEAw5j/ykG3Xp7Pi/qWy6YtijLQX2drBcYwWN6Asjmiala4OGD9P" +
                "AisvYAsmMQKBgC5D4o7LoKrgEd9CTT0Ol2CTpjn4q3YOuqndYF03qHyCarmtJEKC" +
                "ztqCQxEjAJ/mFXqhvdOy3hR6AhumkeWON02AR5HCO/OxbKhyfLk5P4KUCOzFFvPL" +
                "PhCUOaE5ydWyvDmBClz1YHDPxhMiWs0BD8ew10jKdllauyYGFRQ1AyZtAoGBAIBh" +
                "J1txzqaMMRXSu7YyoJB3Rq8bJED8wzo735d3m77vJsgEjUaUdm98B+MdSSFXZlqK" +
                "OKwinbhzkSX3D/eeCRxjGOcExLWJBi/rnl5RISs1J0TD5owwmI/cjDoFAjZcuXVb" +
                "T6fE1ewkMxWy3+JwU4KAuLkwWY3UlMvjERn58pzBAoGBAJHYql7i+sV39z30l9bz" +
                "sMXKpiln6Vvk8WlBh5g8BeeQKcRAaKaIc7i17G7PpbC+sZDnjVl58BbmUjHz+153" +
                "ZZXEuUc7A3B24L/h1v0recSVyvwu1Ye8IuX8oKz9qM2hNOMnX+y1wc6oQ9sCUmw5" +
                "zCaCoV4d6AO6D7AuKEE6tMI8" +
                "-----END PRIVATE KEY-----";

        rsaPrivateKey = rsaPrivateKey.replace("-----BEGIN PRIVATE KEY-----", "");
        rsaPrivateKey = rsaPrivateKey.replace("-----END PRIVATE KEY-----", "");

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(rsaPrivateKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privKey = kf.generatePrivate(keySpec);
        return privKey;
    }

    public static Jws<Claims> parseJwt(String jwtString) throws InvalidKeySpecException, NoSuchAlgorithmException {

        PublicKey publicKey = getPublicKey();

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(jwtString);
        return jwt;
    }

    private static PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String rsaPublicKey = "-----BEGIN PUBLIC KEY-----" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmudMIgkBs5XirXDJMAmq" +
                "OJeN5+LV8C5m2MkueVCAADkb40uLhbkNq5HMqK2y3kcjBIcUG0aDdALQC/xA0maA" +
                "sha1I/yvNwnvaHTER8GTWihjlF49p0DsATk8aHzt3zGDBpPB5u/FvfuwFbKrrZyx" +
                "aOWrwij235HFv5lyQlKqKEmOj+fGN/vQkFTECRtWXgumYhF8OgGKB5WHTrVqzgZ0" +
                "6RA4GBQwA93G+yp5Hd8ktkT3YlMy/uXIT0loXbF+l/yHIJ8jH8YbOyYmOLrzSOLa" +
                "WuTD1Drklv9lOqCE+dAa2MFTOnuUf5EIk7e9Tzk6/hitr5as8aw1XTY7g/zjEbwR" +
                "xQIDAQAB" +
                "-----END PUBLIC KEY-----";
        rsaPublicKey = rsaPublicKey.replace("-----BEGIN PUBLIC KEY-----", "");
        rsaPublicKey = rsaPublicKey.replace("-----END PUBLIC KEY-----", "");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(rsaPublicKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(keySpec);
        return publicKey;
    }

    public void verify(String authorization) throws Exception {
        PublicKey publicKey = getPublicKey();
        System.out.println("authorization: " +authorization);
        try {
            Jwts.parserBuilder().setSigningKey(publicKey).build()
                    .parseClaimsJws(authorization);
        } catch (Exception e) {
            throw new AccessDeniedException("Access Denied");
        }
    }
}
