package com.ridango.account;

import java.util.Optional;

import com.ridango.payment.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account handlePayment(Payment payment) {
        Optional<Account> senderAccount = accountRepository.findById(payment.getSenderAccountId());
        Optional<Account> reciverAccount = accountRepository.findById(payment.getReciverAccountId());
        Account result = null;
        if(senderAccount.isPresent()){
            result = adjustAccountBalance(senderAccount.get(), payment.getAmount());
        }else{
            //TODO Exception
        }
        
        if(reciverAccount.isPresent()){
            result = adjustAccountBalance(reciverAccount.get(), payment.getAmount());
        }else{
            //TODO Exception
        }

        return result;
	}

    private Account adjustAccountBalance(Account account, String amount) {
        Account result = null;
        if(Integer.parseInt(account.getBalance()) > Integer.parseInt(amount)){
            result = accountRepository.save(account);
        }else{
            // TODO Exception
        }
        return result;
    }

}
