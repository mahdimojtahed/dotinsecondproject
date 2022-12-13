package org.example.src;

import java.math.BigInteger;

public class Transaction {
    private String id;
    private String type;
    private BigInteger amount;
    private String deposit;

    public Transaction(String id, String type, BigInteger amount, String deposit) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.deposit = deposit;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public BigInteger getAmount() {
        return amount;
    }
    public void setAmount(BigInteger amount) {
        this.amount = amount;
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
                ", amount='" + amount + '\'' +
                ", deposit='" + deposit + '\'' +
                '}';
    }
}
