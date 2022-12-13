package org.example.src;

import java.math.BigInteger;

public class Deposit {
    private String customer;
    private String id;
    private BigInteger initialBalance;
    private BigInteger upperBound;
    public Deposit(String customer, String id, BigInteger initialBalance, BigInteger upperBound) {
        this.customer = customer;
        this.id = id;
        this.initialBalance = initialBalance;
        this.upperBound = upperBound;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigInteger getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigInteger initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigInteger getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(BigInteger upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "customer='" + customer + '\'' +
                ", id='" + id + '\'' +
                ", initialBalance='" + initialBalance + '\'' +
                ", upperBound='" + upperBound + '\'' +
                '}';
    }
}