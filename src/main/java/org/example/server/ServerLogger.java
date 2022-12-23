package org.example.server;

import org.example.resources.Strings;
import org.example.server.config.Configuration;
import org.example.src.Deposit;
import org.example.src.Transaction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;

public class ServerLogger {
    static File file = new File("src\\main\\log\\" + Configuration.getOutLog());

    static void logger(Socket socket, Deposit deposit, Transaction transaction) {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(
                    "client connected " +
                            "-Port:" + socket.getPort() +
                            "-Ip:" + socket.getInetAddress() +
                            "-Customer:" + deposit.getCustomer() +
                            "-Transaction-Amount:" + transaction.getAmount() +
                            "-Transaction-Type:" + transaction.getType() +
                            "-New Balance:" + deposit.getInitialBalance() +
                            "-Time:" + LocalTime.now()
            );
            bw.newLine();
        } catch (IOException e) {
            System.out.println(Strings.RES_ERROR);
        }
    }
}
