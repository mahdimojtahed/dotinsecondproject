package org.example;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void connectToServer() {

        // todo: get port and host address from terminal.xml file
        try (Socket socket = new Socket("localhost", 8080)) {

            BufferedReader readTerminal = new BufferedReader(new FileReader("src\\main\\resources\\terminal.xml"));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = readTerminal.readLine()) != null) {
                stringBuilder.append(line);
            }


            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(stringBuilder.toString());
            // todo: log events in term21375.log


            InputStream inputStream = socket.getInputStream();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream));
            String response = responseReader.readLine();

            File file = new File("response.xml");
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))) {
//                bw.write();
            } catch (IOException e) {
                System.out.println(e);
            }


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