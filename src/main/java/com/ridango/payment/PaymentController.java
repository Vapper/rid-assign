package com.ridango.payment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    
    @PostMapping("/payment")
    public String paymentSubmit(@RequestBody IncomingPayment incomingPayment) {
        try {
            logger.info(incomingPayment.toString() + " in Payment Controller");
            paymentService.savePayment(incomingPayment);
        } catch (NegativePaymentAmountException e) {
            return "Payment amount must be above 0";
        } catch (MissingAccountException e) {
            return "Account is missing";
        }
        
        return "Success";
    }

    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

}
