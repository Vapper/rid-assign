package com.ridango.account;

import java.util.List;
import java.util.Optional;

import com.ridango.payment.IncomingPayment;
import com.ridango.payment.MissingAccountException;

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
        if(senderAccount.isPresent()){
            if(checkBalance(senderAccount.get(), incomingPayment.getAmountAsInt())){
                result = adjustAccountBalance(senderAccount.get(), incomingPayment.getAmountAsInt());
            }else{
                throw new NegativeBalanceException();
            }
        }else{
            throw new MissingAccountException();
        }
        
        if(reciverAccount.isPresent()){
            result = adjustAccountBalance(reciverAccount.get(), -incomingPayment.getAmountAsInt());
        }else{
            throw new MissingAccountException();
        }
        return result;
    }
    
    private boolean checkBalance(Account account, int amount){
        return account.getBalance() >= amount;
    }

    private Account adjustAccountBalance(Account account, int amount) {
        int newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        Account result = accountRepository.save(account);
        return result;
    }

	public List<Account> getAllAccounts() {
		return (List<Account>) accountRepository.findAll();
	}

}
