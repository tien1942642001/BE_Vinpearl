package dev.tienvv.demo.BE_Vinpearl.service;

import dev.tienvv.demo.BE_Vinpearl.model.PaymentRequest;

import java.io.UnsupportedEncodingException;

public interface PaymentService {
    String createPaymentUrl(PaymentRequest paymentRequest) throws UnsupportedEncodingException;
    boolean verifyPayment(String vnpResponse);
}
