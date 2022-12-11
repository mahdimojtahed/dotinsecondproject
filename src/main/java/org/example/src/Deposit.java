package org.example.src;

public class Deposit {
    private String customer;
    private String id;
    private String initialBalance;
    private String upperBound;
    public Deposit(String customer, String id, String initialBalance, String upperBound) {
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

    public String getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(String initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(String upperBound) {
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