package org.example.server;

import org.example.exeptionHandling.GenericExceptions;
import org.example.resources.Strings;
import org.example.server.config.Configuration;
import org.example.util.JsonReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int serverPort = 0;
        try {
            JsonReader.readJSON("src/main/resources/core.json");
            serverPort = Configuration.getPort();
        } catch (GenericExceptions e) {
            System.out.println(e.getMessage());
        }

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println(Strings.SERVER_LISTEN + serverPort);

            while (true) {
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
