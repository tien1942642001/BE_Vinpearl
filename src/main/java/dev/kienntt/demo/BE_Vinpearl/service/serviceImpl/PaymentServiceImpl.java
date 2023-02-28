package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.config.VnPayConfig;
import dev.kienntt.demo.BE_Vinpearl.config.VnPayUtils;
import dev.kienntt.demo.BE_Vinpearl.model.PaymentRequest;
import dev.kienntt.demo.BE_Vinpearl.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final String PAYMENT_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    private static final String QUERY_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";

    @Autowired
    private VnPayConfig vnPayConfig;

    @Value("${vnpay.tmncode}")
    private String tmnCode;

    @Value("${vnpay.hashsecret}")
    private String hashSecret;

    @Value("${vnpay.paymentUrl}")
    private String paymentUrl;

    @Override
    public String createPaymentUrl(PaymentRequest paymentRequest) throws UnsupportedEncodingException {
        String vnp_Returnurl = vnPayConfig.getReturnUrl();
        String vnp_TmnCode = vnPayConfig.getTmnCode();
        String vnp_HashSecret = vnPayConfig.getHashSecret();
        String vnp_Url = vnPayConfig.getUrl();
        String vnp_Version = vnPayConfig.getVersion();
        String vnp_Command = vnPayConfig.getCommand();
//        String vnp_TxnRef = VnPayUtils.getRandomNumber(8);
        String vnp_OrderInfo = paymentRequest.getOrderInfo();
        long vnp_Amount = paymentRequest.getAmount() * 100;
        String vnp_IpAddr = paymentRequest.getIp();
        String vnp_CurrCode = "VND";
        String vnp_Locale = "vn";
        String vnp_TxnTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Locale", vnp_Locale);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(vnp_Amount));
        vnp_Params.put("vnp_CurrCode", vnp_CurrCode);
//        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_TxnRef", UUID.randomUUID().toString().replace("-", ""));
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_ReturnUrl", vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_CreateDate", vnp_TxnTime);
//        String vnp_SecureHashType = "SHA256";
//        vnp_Params.put("vnp_SecureHashType", vnp_SecureHashType);

//        vnp_Params.put("vnp_SecureHash", vnp_SecureHash);
//
//        String vnp_Url_Params = VnPayUtils.buildQueryUrl(vnp_Params);
//
//        String redirectUrl = vnp_Url + "?" + vnp_Url_Params;

//        return redirectUrl;

//        String vnp_SecureHash = VnPayUtils.hashAllFields(vnp_Params, vnp_HashSecret);
        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();

        String vnp_SecureHash = VnPayUtils.hmacSHA512(vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = vnp_Url + "?" + queryUrl;
        return paymentUrl;
    }

    @Override
    public boolean verifyPayment(String vnpResponse) {
//        String vnp_HashSecret = vnPayConfig.getHashSecret();
//        String vnp_SecureHash = vnPayResponse.getVnp_SecureHash();
//        vnPayResponse.setVnp_SecureHash(null);
//        Map<String, String> vnp_Params = VnPayUtils.convertUrlEncodedStringToMap(vnPayResponse.toString());
//        String signValue = VnPayUtils.hashAllFields(vnp_Params, vnp_HashSecret);
//        if (vnp_SecureHash) {
//            return vnp_SecureHash.equals(signValue);
//        }
        return true;
    }

    private String getHashData(Map<String, String> params) {
        // Sort parameters by key
        TreeMap<String, String> sortedParams = new TreeMap<>(params);

        // Concatenate sorted parameters into a single string
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    private String hmacSHA512(String data, String key) {
        try {
            Mac sha512Hmac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            sha512Hmac.init(secretKey);

            byte[] hmacData = sha512Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(hmacData).toLowerCase();
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            throw new RuntimeException("Failed to generate HMAC-SHA512", ex);
        }
    }

    private Map<String, String> parseVnpayResponse(String response) {
        Map<String, String> params = new HashMap<>();
        for (String pair : response.split("&")) {
            String[] parts = pair.split("=", 2);
            params.put(parts[0], parts[1]);
        }
        return params;
    }
}
