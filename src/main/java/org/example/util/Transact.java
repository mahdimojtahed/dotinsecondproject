package org.example.util;

import org.example.resources.Strings;
import org.example.src.Deposit;
import org.example.src.Transaction;

import java.util.List;

public class Transact {
    static List<Deposit> deposits = JsonReader.getDeposits();

    synchronized public static Deposit transact(Transaction transaction) {

        switch (transaction.getType()) {
            case Strings.DEPOSIT:
                for (Deposit deposit : deposits) {
                    if (deposit.getId().equals(transaction.getDeposit()) &&
                            Validation.validator(transaction)) {

                        deposit.setInitialBalance(deposit.getInitialBalance().add(transaction.getAmount()));
                        return deposit;
                    }
                }
                break;

            case Strings.WITHDRAW:
                for (Deposit deposit : deposits) {
                    if (deposit.getId().equals(transaction.getDeposit()) &&
                            Validation.validator(transaction)) {
                        deposit.setInitialBalance(deposit.getInitialBalance().subtract(transaction.getAmount()));
                        return deposit;
                    }
                }
                break;
        }

        return null;
    }
}
