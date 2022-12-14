package org.example.util;

import org.example.resources.Strings;
import org.example.src.Deposit;
import org.example.src.Transaction;

import java.util.ArrayList;

public class Transact {
    synchronized public static Deposit transact(Transaction transaction, ArrayList<Deposit> deposits) {

        if (transaction.getType().equals(Strings.DEPOSIT)) {
            for (Deposit deposit : deposits) {
                if (deposit.getId().equals(transaction.getDeposit())) {
                    deposit.setInitialBalance(deposit.getInitialBalance().add(transaction.getAmount()));
                    return deposit;
                }
            }
        }

        if (transaction.getType().equals(Strings.WITHDRAW)) {
            for (Deposit deposit : deposits) {
                if (deposit.getId().equals(transaction.getDeposit())) {
                    deposit.setInitialBalance(deposit.getInitialBalance().subtract(transaction.getAmount()));
                    return deposit;
                }
            }
        }
        return null;
    }
}
