package org.example;

import org.example.resources.Strings;
import org.example.util.InputHandler;
import org.jdom2.Document;
import org.jdom2.Element;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client {
    public static void connectToServer() {
        BufferedReader readTerminal;
        StringBuilder stringBuilder;
        int port = 0;
        String ip = null;
        String path = null;

        try {
            readTerminal = new BufferedReader(new FileReader("src\\main\\resources\\terminal.xml"));
            String line;
            stringBuilder = new StringBuilder();
            while ((line = readTerminal.readLine()) != null) {
                stringBuilder.append(line);
            }

            Document doc = InputHandler.convertStringToDocument(stringBuilder.toString());
            Element rootNode = doc.getRootElement();
            List<Element> list = rootNode.getChildren();

            for (Element el : list) {
                if (el.getName().equals(Strings.ATTR_STR_SERVER)) {
                    port = Integer.parseInt(el.getAttributeValue(Strings.ATTR_STR_PORT));
                    ip = el.getAttributeValue(Strings.ATTR_STR_IP);
                } else if (el.getName().equals(Strings.ATTR_STR_OUTLOG)) {
                    path = el.getAttributeValue(Strings.ATTR_STR_PATH);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Socket socket = new Socket(ip, port)) {

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(stringBuilder);

            InputStream inputStream = socket.getInputStream();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream));
            String response = responseReader.readLine();

            File file = new File("src//main//log//" + path);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {

                if (response != null) {
                    bw.write(response);
                    bw.newLine();
                    System.out.println(response);
                } else {
                    System.out.println(Strings.DONE);
                }


            } catch (IOException e) {
                System.out.println(Strings.RES_ERROR);
            }
        } catch (IOException e) {
            System.out.println(Strings.SERVER_DOWN);
        }
    }

    public static void main(String[] args) {
        Client.connectToServer();
    }
}