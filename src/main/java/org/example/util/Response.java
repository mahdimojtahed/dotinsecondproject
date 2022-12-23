package org.example.util;

import org.example.resources.Strings;
import org.example.src.Deposit;
import org.example.src.Transaction;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;

public class Response {

    public static void handleResponse(Socket socket, Deposit deposit, Transaction transaction) {

        try {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(
                    Strings.CONNECTION_ESTABLISHED +
                            Strings.CUSTOMER + deposit.getCustomer() +
                            Strings.TRANSACTION_AMOUNT + transaction.getAmount() +
                            Strings.TRANSACTION_TYPE + transaction.getType() +
                            Strings.NEW_BALANCE + deposit.getInitialBalance() +
                            Strings.TIME + LocalTime.now()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
