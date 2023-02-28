//package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;
//
//import dev.kienntt.demo.BE_Vinpearl.service.OnepayService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OnepayServiceImpl implements OnepayService {
//    @Override
//    public String buildCheckoutUrl(String orderId, long amount) {
//        OnepayConfig.setVnPayConfig(ACCESS_CODE, SECURE_SECRET, true);
//        String returnUrl = "http://localhost:8080/onepay/return";
//        String cancelUrl = "http://localhost:8080/onepay/cancel";
//        return OnepayPayment.createCheckoutUrl(orderId, amount, returnUrl, cancelUrl);
//    }
//
//    @Override
//    public OnepayResult verifyPayment(String queryString) {
//        OnepayConfig.setVnPayConfig(ACCESS_CODE, SECURE_SECRET, true);
//        return OnepayPayment.verifyPayment(queryString);
//    }
//}
