package com.ridango.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    
    @PostMapping("/payment")
    public String paymentSubmit(@RequestBody IncomingPayment incomingPayment) {
        try {
            paymentService.savePayment(incomingPayment);
        } catch (NegativePaymentAmountException e) {
            return "Payment amount must be above 0";
        }
        
        return "Success";
    }

    @GetMapping("/payment")
    public String paymentSubmitGet() {
        return "Get";
    }

}
