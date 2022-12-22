package org.example.src;

import java.math.BigInteger;

public class Transaction {
    private String id;
    private String type;
    private BigInteger amount;
    private String deposit;
    private boolean isDone;

    public Transaction(String id, String type, BigInteger amount, String deposit, boolean isDone) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.deposit = deposit;
        this.isDone = isDone;
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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", deposit='" + deposit + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}
