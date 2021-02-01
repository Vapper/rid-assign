package com.ridango.payment;

import java.util.List;

import com.ridango.account.Account;
import com.ridango.account.AccountService;
import com.ridango.exceptions.*;

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

    public Payment savePayment(IncomingPayment incomingPayment) throws NegativePaymentAmountException, MissingAccountException, NegativeBalanceException, NumberFormatException {

        if (incomingPayment.amountAsInt() < 0) {
            throw new NegativePaymentAmountException();
        }

        Payment payment = new Payment(
            incomingPayment.getSenderAccountId(), 
            incomingPayment.getReceiverAccountId()
        );
        
        Account accountResult = accountService.handlePayment(incomingPayment);
        Payment result = paymentRepository.save(payment);
        return result;
    }

	public List<Payment> getAllPayments() {
        return (List<Payment>) paymentRepository.findAll();
	}

}
