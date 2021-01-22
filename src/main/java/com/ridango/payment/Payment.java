package com.ridango.payment;

public class Payment {
    
    private long senderAccountId;
    private long reciverAccountId;
    private String amount;

    public Payment() {
    }

    public Payment(long senderAccountId, long reciverAccountId, String amount) {
        this.senderAccountId = senderAccountId;
        this.reciverAccountId = reciverAccountId;
        this.amount = amount;
    }
    
    public long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public long getReciverAccountId() {
        return reciverAccountId;
    }

    public void setReciverAccountId(long reciverAccountId) {
        this.reciverAccountId = reciverAccountId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    



}
