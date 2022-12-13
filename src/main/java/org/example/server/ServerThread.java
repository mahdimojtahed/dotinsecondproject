package org.example.server;

import org.example.resources.Strings;
import org.example.src.Deposit;
import org.example.src.Transaction;
import org.example.util.*;
import org.jdom2.Document;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    static ArrayList<Deposit> deposits = new ArrayList<>();
    static ArrayList<Transaction> transactions = new ArrayList<>();
    private Socket socket;

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


            for (Transaction transaction : transactions) {
                if (Validation.validator(transaction, deposits)) {
                    Deposit deposit = Transact.transact(transaction, deposits);
                    writer.println(deposit);
                }
            }

            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
