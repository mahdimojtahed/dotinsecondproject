package org.example.server;

import org.example.resources.Strings;
import org.example.server.config.Configuration;
import org.example.src.Deposit;
import org.example.src.Transaction;
import org.example.util.*;
import org.jdom2.Document;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;

public class ServerThread extends Thread {
    static ArrayList<Deposit> deposits = new ArrayList<>();
    static ArrayList<Transaction> transactions = new ArrayList<>();
    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            System.out.println(Strings.NEW_CLIENT + " " + Thread.currentThread().getName());

            BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientMessage = clientReader.readLine();
            Document doc = InputHandler.convertStringToDocument(clientMessage);
            XMLParser.initTransactions(doc);

            transactions = XMLParser.getTransactions();
            deposits = JsonReader.getDeposits();

            File file = new File("src\\main\\log\\" + Configuration.getOutLog());
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                bw.write(
                        "client connected " +
                                " Port:" + socket.getPort() +
                                "-Ip:" + socket.getInetAddress() +
                                "-Time:" + LocalTime.now());
                bw.newLine();
            } catch (IOException e) {
                System.out.println(Strings.RES_ERROR);
            }


            for (Transaction transaction : transactions) {
                if (Validation.validator(transaction, deposits)) {
                    Deposit deposit = Transact.transact(transaction, deposits);
                    System.out.println(deposit);

                    writer.println(deposit);
                }
            }

            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
