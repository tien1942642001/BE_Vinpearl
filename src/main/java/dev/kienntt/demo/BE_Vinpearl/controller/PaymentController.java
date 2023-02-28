package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.model.PaymentRequest;
import dev.kienntt.demo.BE_Vinpearl.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/order")
    public ModelAndView showOrder(@RequestParam String orderId, @RequestParam double amount) {
        ModelAndView mav = new ModelAndView("order");
        mav.addObject("orderId", orderId);
        mav.addObject("amount", amount);
        return mav;
    }

//    @PostMapping("/checkout")
//    public ModelAndView checkout(@RequestParam String orderId, @RequestParam double amount) {
//        String redirectUrl = paymentService.createPaymentUrl(orderId, amount);
//        return new ModelAndView("redirect:" + redirectUrl);
//    }

//    @GetMapping("/callback")
//    public ModelAndView handleCallback(@RequestParam Map<String, String> params) {
//        boolean success = paymentService.verifyPayment(params);
//        if (success) {
//            // Payment successful, update order status and display success page
//            return new ModelAndView("success");
//        } else {
//            // Payment failed or verification failed, display error page
//            return new ModelAndView("error");
//        }
//    }

    @PostMapping("/create-payment-url")
    public ResponseEntity<String> createPaymentUrl(@RequestBody PaymentRequest paymentRequest) throws UnsupportedEncodingException {
        String redirectUrl = paymentService.createPaymentUrl(paymentRequest);
        return ResponseEntity.ok(redirectUrl);
    }

    @PostMapping("")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) throws UnsupportedEncodingException {
        String paymentUrl = paymentService.createPaymentUrl(paymentRequest);
        return ResponseEntity.ok(paymentUrl);
    }

    @RequestMapping(value = "/payment-return", method = RequestMethod.GET)
    public String handlePaymentReturn(@RequestParam(name = "vnpay_response") String vnpResponse, Model model) {
        boolean paymentSuccess = paymentService.verifyPayment(vnpResponse);
        if (paymentSuccess) {
            // Payment successful, do something (e.g. update order status, show confirmation page)
            model.addAttribute("paymentStatus", "success");
        } else {
            // Payment not successful, do something else (e.g. show error message)
            model.addAttribute("paymentStatus", "failed");
        }
        return "payment-return-page";
    }
}