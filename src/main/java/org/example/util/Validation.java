package org.example.util;

import org.example.resources.Strings;
import org.example.src.Deposit;
import org.example.src.Transaction;

import java.util.ArrayList;

public class Validation {
    static boolean isAlreadyDone = false;
    static boolean isValid = true;
    public static boolean validator(Transaction transaction, ArrayList<Deposit> deposits) {
        for (Deposit deposit : deposits) {
            if (transaction.getDeposit().equals(deposit.getId())) {
                if (transaction.getType().equals(Strings.DEPOSIT)) {
                    if ((transaction.getAmount()).add(deposit.getInitialBalance()).compareTo(deposit.getUpperBound()) > 0) {
                        isValid = false;
                    }
                } else if (transaction.getType().equals(Strings.WITHDRAW)) {
                    if (transaction.getAmount().compareTo(deposit.getInitialBalance()) > 0) {

                        isValid = false;
                    }
                }
            }
        }
        return isValid;
    }

    static boolean checkDone(String id, ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                isAlreadyDone = true;
                break;
            }
        }
        return isAlreadyDone;
    }
}
