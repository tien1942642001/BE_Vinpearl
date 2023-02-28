package dev.kienntt.demo.BE_Vinpearl.config;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class VnPayUtils {

    public static String hmacSHA512(final String key, final String data) {
        try {

            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }
    public static String hashAllFields(Map<String, String> fields, String secretKey) {
        StringBuilder hashData = new StringBuilder();
        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);
        for (String fieldName : fieldNames) {
            String fieldValue = fields.get(fieldName);
            if (fieldValue != null && fieldValue.length() > 0) {
                hashData.append(fieldName).append("=").append(fieldValue).append("&");
            }
        }
        hashData.setLength(hashData.length() - 1);
        String secureHash = sha256(secretKey + hashData.toString());
        return secureHash;
    }

    public static String sha256(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes("UTF-8"));
            byte[] digest = md.digest();
            return String.format("%064x", new BigInteger(1, digest));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String buildQueryUrl(Map<String, String> fields) {
        StringBuilder queryUrl = new StringBuilder();
        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);
        for (String fieldName : fieldNames) {
            String fieldValue = fields.get(fieldName);
            if (fieldValue != null && fieldValue.length() > 0) {
                try {
                    queryUrl.append(fieldName).append("=").append(URLEncoder.encode(fieldValue, "UTF-8")).append("&");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        queryUrl.setLength(queryUrl.length() - 1);
        return queryUrl.toString();
    }

    public static Map<String, String> convertUrlEncodedStringToMap(String urlEncodedString) {
        try {
            Map<String, String> map = new HashMap<>();
            String[] pairs = urlEncodedString.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                map.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                        URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
            return map;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ipAdress;
        try {
            ipAdress = request.getHeader("X-FORWARDED-FOR");
            if (ipAdress == null) {
                ipAdress = request.getLocalAddr();
            }
        } catch (Exception e) {
            ipAdress = "Invalid IP:" + e.getMessage();
        }
        return ipAdress;
    }
}
