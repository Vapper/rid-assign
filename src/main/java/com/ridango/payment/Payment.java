package com.ridango.payment;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Payment {
    
    @Id
    private long id;
    private long senderAccountId;
    private long reciverAccountId;

    public Payment() {
    }

    public Payment(long id, long senderAccountId, long reciverAccountId) {
        this.id = id;
        this.senderAccountId = senderAccountId;
        this.reciverAccountId = reciverAccountId;
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

    public long getReciverAccountId() {
        return reciverAccountId;
    }

    public void setReciverAccountId(long reciverAccountId) {
        this.reciverAccountId = reciverAccountId;
    }

}
