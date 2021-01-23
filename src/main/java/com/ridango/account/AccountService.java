package com.ridango.account;

import java.util.List;
import java.util.Optional;

import com.ridango.payment.IncomingPayment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    public Account handlePayment(IncomingPayment incomingPayment) {
        Optional<Account> senderAccount = accountRepository.findById(incomingPayment.getSenderAccountId());
        Optional<Account> reciverAccount = accountRepository.findById(incomingPayment.getReceiverAccountId());
        Account result = null;
        logger.info("Before is present");
        if(senderAccount.isPresent()){
            result = adjustAccountBalance(senderAccount.get(), incomingPayment.getAmount());
        }else{
            logger.info("senderAccount not present");
            //TODO Exception
        }
        
        if(reciverAccount.isPresent()){
            result = adjustAccountBalance(reciverAccount.get(), incomingPayment.getAmount());
        }else{
            logger.info("receiverAccount not present");
        }

        return result;
	}

    private Account adjustAccountBalance(Account account, String amount) {
        Account result = null;
        if (account.getBalance() > Integer.parseInt(amount)) {
            result = accountRepository.save(account);
        }else{
            logger.info("ERROR IN ACCOUNT");
        }
        return result;
    }

	public List<Account> getAllAccounts() {
		return (List<Account>) accountRepository.findAll();
	}

}
