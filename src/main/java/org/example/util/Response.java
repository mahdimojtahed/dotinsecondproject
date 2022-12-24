package org.example.util;

import org.example.resources.Strings;
import org.example.src.Deposit;
import org.example.src.Transaction;

import java.time.LocalTime;

public class Response {

    public static void handleResponse(StringBuilder str, Deposit deposit, Transaction transaction) {
        String string = Strings.CONNECTION_ESTABLISHED + Strings.CUSTOMER +
                deposit.getCustomer() +
                Strings.OLD_BALANCE + deposit.getInitialBalance().subtract(transaction.getAmount()) +
                Strings.TRANSACTION_TYPE + transaction.getType() +
                Strings.TRANSACTION_AMOUNT + transaction.getAmount() +
                Strings.NEW_BALANCE + deposit.getInitialBalance() +
                Strings.TIME + LocalTime.now() +
                "BREAK";

        str.append(string);

    }
}
