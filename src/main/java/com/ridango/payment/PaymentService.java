package com.ridango.payment;

import com.ridango.account.Account;
import com.ridango.account.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private AccountService accountService;

    public Payment savePayment(IncomingPayment incomingPayment) throws NegativePaymentAmountException {
        if (incomingPayment.getAmount() > 0) {
            throw new NegativePaymentAmountException();
        }
        //Payment result = paymentRepository.save(payment);
        //Account accountResult = accountService.handlePayment(payment);
        Payment result = null;
        return result;
    }

}
