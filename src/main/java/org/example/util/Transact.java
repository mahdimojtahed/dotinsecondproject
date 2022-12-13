package org.example.util;

import org.example.resources.Strings;
import org.example.src.Deposit;
import org.example.src.Transaction;

import java.util.ArrayList;

public class Transact {

    synchronized public static Deposit transact(Transaction transaction, ArrayList<Deposit> deposits) {
        for (Deposit deposit : deposits) {
            if (transaction.getDeposit().equals(deposit.getId())) {

                if (transaction.getType().equals(Strings.DEPOSIT)) {
                    deposit.setInitialBalance(deposit.getInitialBalance().add(transaction.getAmount()));
                } else if (transaction.getType().equals(Strings.WITHDRAW)) {
                    deposit.setInitialBalance(deposit.getInitialBalance().subtract(transaction.getAmount()));
                }

            }
            return deposit;
        }
        return null;
    }
}
