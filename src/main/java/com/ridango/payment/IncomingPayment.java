package com.ridango.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncomingPayment {

    private Long senderAccountId;
    private Long receiverAccountId;
    private String amount;

    public IncomingPayment(Long senderAccountId, Long receiverAccountId, String amount) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
    }

    public IncomingPayment() {
    }

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int amountAsInt() throws NumberFormatException{
        if(!amount.contains(".") ||  (amount.subSequence(amount.indexOf(".")+1,amount.length()).length() != 2)){
            throw new NumberFormatException();
        }
        return Integer.parseInt(amount.replaceAll("\\.", ""));
    }

    
}
