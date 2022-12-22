package org.example.util;

import org.example.resources.Strings;
import org.example.src.Deposit;
import org.example.src.Transaction;

import java.math.BigInteger;
import java.util.List;

public class Validation {
    static List<Deposit> deposits = JsonReader.getDeposits();
    static boolean isValid;
    public static boolean validator(Transaction transaction) {

        for (Deposit deposit : deposits) {
            isValid = true;
            if (transaction.getDeposit().equals(deposit.getId())) {
                switch (transaction.getType()) {
                    case Strings.DEPOSIT:
                        if (transaction.getAmount().add(deposit.getInitialBalance()).compareTo(deposit.getUpperBound()) > 0) {
                            isValid = false;
                        }
                        break;
                    case Strings.WITHDRAW:
                        if(transaction.getAmount().subtract(deposit.getInitialBalance()).compareTo(BigInteger.valueOf(0)) < 0 ) {
                            isValid = false;
                        }
                        break;
                }

            }
        }
        return isValid;
    }


}
