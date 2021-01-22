package com.ridango.account;

public class Account {

    private long id;
    private String balance;

    public Account() {
    }

    public Account(long id, String balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
