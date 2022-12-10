package org.example.server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            System.out.println("New Server thread started " + Thread.currentThread().getName());
            writer.println("Hello");
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
