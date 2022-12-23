package org.example.server;

import org.example.resources.Strings;
import org.example.src.Deposit;
import org.example.src.Transaction;
import org.example.util.InputHandler;
import org.example.util.Response;
import org.example.util.Transact;
import org.example.util.XMLParser;
import org.jdom2.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            // todo : delete souts
            System.out.println(Strings.NEW_CLIENT + " " + Thread.currentThread().getName());
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientMessage = clientReader.readLine();
            Document doc = InputHandler.convertStringToDocument(clientMessage);
            XMLParser.initTransactions(doc);

            transactions = XMLParser.getTransactions();
            for (Transaction transaction : transactions) {
                Deposit deposit = Transact.transact(transaction);
                if (deposit != null) {
                    ServerLogger.logger(socket, deposit, transaction);
                    Response.handleResponse(socket, deposit, transaction);
                }
            }

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

