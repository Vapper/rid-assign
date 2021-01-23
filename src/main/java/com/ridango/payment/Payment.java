package com.ridango.payment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Payment {
    
    @Id
    @GeneratedValue()
    private long id;
    private long senderAccountId;
    private long receiverAccountId;

    public Payment() {
    }

    public Payment(long senderAccountId, long receiverAccountId) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    

}
