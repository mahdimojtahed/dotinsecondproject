package org.example.server;

import org.example.exeptionHandling.GenericExceptions;
import org.example.resources.Strings;
import org.example.util.JsonReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();
        int serverPort = 0;
        try {
            serverPort = jsonReader.readJSON("src/main/resources/config.json");
        } catch (GenericExceptions e) {
            System.out.println(e.getMessage());
        }

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println(Strings.SERVER_LISTEN + serverPort);
            while (true) {
                Socket socket = serverSocket.accept();

                new ServerThread(socket).start();
                System.out.println(Strings.NEW_CLIENT);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
