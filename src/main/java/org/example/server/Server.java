package org.example.server;

import org.example.exeptionHandling.GenericExceptions;
import org.example.resources.Strings;
import org.example.server.config.Configuration;
import org.example.src.Deposit;
import org.example.src.Transaction;
import org.example.util.InputHandler;
import org.example.util.JsonReader;
import org.example.util.XMLParser;
import org.jdom2.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
        static ArrayList<Deposit> deposits = new ArrayList<>();
        static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();

        int serverPort = 0;
        try {
            deposits = jsonReader.readJSON("src/main/resources/core.json");
            serverPort = Configuration.getPort();
        } catch (GenericExceptions e) {
            System.out.println(e.getMessage());
        }

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println(Strings.SERVER_LISTEN + serverPort);

            while (true) {
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();
                System.out.println(Strings.NEW_CLIENT);

                BufferedReader clientReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                String clientMessage = clientReader.readLine();
                Document doc = InputHandler.convertStringToDocument(clientMessage);


                transactions = XMLParser.initTransactions(doc);
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
