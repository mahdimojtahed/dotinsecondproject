package org.example.src;

import java.math.BigInteger;

public class Transaction {
    private final String id;
    private final String type;
    private final BigInteger amount;
    private String deposit;

    public Transaction(String id, String type, BigInteger amount, String deposit) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.deposit = deposit;
    }

    public String getType() {
        return type;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", deposit='" + deposit + '\'' +
                '}';
    }
}
