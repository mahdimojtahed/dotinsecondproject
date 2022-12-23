package org.example.src;

import java.math.BigInteger;

public class Deposit {
    private final String customer;
    private final String id;
    private BigInteger initialBalance;
    private final BigInteger upperBound;

    public Deposit(String customer, String id, BigInteger initialBalance, BigInteger upperBound) {
        this.customer = customer;
        this.id = id;
        this.initialBalance = initialBalance;
        this.upperBound = upperBound;
    }

    public String getCustomer() {
        return customer;
    }

    public String getId() {
        return id;
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