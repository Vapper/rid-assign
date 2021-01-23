package com.ridango.payment;

import java.util.List;

import com.ridango.account.NegativeBalanceException;

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
            paymentService.savePayment(incomingPayment);
        } catch (NegativePaymentAmountException e1) {
            return "Payment amount must be above 0";
        } catch (MissingAccountException e2) {
            return "Account is missing or incorrectly formatted. Must be numeric Id";
        } catch (NegativeBalanceException e3) {
            return "Not enough money on account";
        } catch (NumberFormatException e4) {
            return "Wrongly formatted amount";
        }
        return "Success";
    }

    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

}
