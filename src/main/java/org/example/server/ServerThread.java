package org.example.server;

import org.example.resources.Strings;
import org.example.src.Deposit;
import org.example.src.Transaction;
import org.example.util.InputHandler;
import org.example.util.Response;
import org.example.util.Transact;
import org.example.util.XMLParser;
import org.jdom2.Document;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    static ArrayList<Transaction> transactions = new ArrayList<>();
    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println(Strings.NEW_CLIENT + " " + Thread.currentThread().getName());
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientMessage = clientReader.readLine();
            Document doc = InputHandler.convertStringToDocument(clientMessage);
            XMLParser.initTransactions(doc);

            transactions = XMLParser.getTransactions();
            StringBuilder str = new StringBuilder();

            for (Transaction transaction : transactions) {
                Deposit deposit = Transact.transact(transaction);
                if (deposit != null) {
                    ServerLogger.logger(socket, deposit, transaction);
                    Response.handleResponse(str, deposit, transaction);
                    System.out.println(deposit);
                }
            }

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(str);



            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

