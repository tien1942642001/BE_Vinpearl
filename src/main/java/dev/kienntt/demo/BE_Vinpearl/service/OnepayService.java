package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.OnepayResult;

public interface OnepayService {
    String createPaymentRequest(String orderId, String amount, String orderInfo, String returnUrl, String cancelUrl);
    boolean confirmPayment(String orderId, String amount);
    boolean cancelPayment(String orderId);
}
