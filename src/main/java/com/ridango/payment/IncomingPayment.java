package com.ridango.payment;

public class IncomingPayment {

    private Long senderAccountId;
    private Long reciverAccountId;
    private String amount;

    public IncomingPayment(Long senderAccountId, Long reciverAccountId, String amount) {
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    
}
