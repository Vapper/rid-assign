package com.ridango.payment;

public class IncomingPayment {

    private Long senderAccountId;
    private Long reciverAccountId;
    private int amount;

    public IncomingPayment(Long senderAccountId, Long reciverAccountId, int amount) {
        this.senderAccountId = senderAccountId;
        this.reciverAccountId = reciverAccountId;
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

    public Long getReciverAccountId() {
        return reciverAccountId;
    }

    public void setReciverAccountId(Long reciverAccountId) {
        this.reciverAccountId = reciverAccountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    
}
