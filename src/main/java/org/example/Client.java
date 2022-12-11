package org.example;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void connectToServer() {
        try (Socket socket = new Socket("localhost", 8080)) {

            BufferedReader reader2 = new BufferedReader(new FileReader("src\\main\\resources\\terminal.xml"));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader2.readLine()) != null) {
                stringBuilder.append(line);
            }

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(stringBuilder.toString());


            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String response = reader.readLine();

            socket.close();
            System.out.println(response);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Client.connectToServer();
    }
}