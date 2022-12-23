package org.example.util;

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
                    "Connection To Server Established : " +
                            "-Customer:" + deposit.getCustomer() +
                            "-Transaction-Amount:" + transaction.getAmount() +
                            "-Transaction-Type:" + transaction.getType() +
                            "-New Balance:" + deposit.getInitialBalance() +
                            "-Time:" + LocalTime.now()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
