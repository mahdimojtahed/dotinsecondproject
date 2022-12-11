package org.example.util;

import com.google.gson.Gson;
import org.example.exeptionHandling.GenericExceptions;
import org.example.server.config.Configuration;
import org.example.src.Deposit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

class JsonContent {
    int port;
    String outLog;
    Deposit[] deposits;
}

public class JsonReader {
    Gson gson = new Gson();

    public ArrayList<Deposit> readJSON(String jsonPath) throws GenericExceptions {
        JsonContent jsonContent;
        ArrayList<Deposit> deposits = new ArrayList<>();

        try {
            jsonContent = gson.fromJson(new FileReader(jsonPath), JsonContent.class);
            Configuration.setPort(jsonContent.port);
            Configuration.setOutLog(jsonContent.outLog);
            for (Deposit d : jsonContent.deposits) {
                deposits.add(
                        new Deposit(
                                d.getCustomer(),
                                d.getId(),
                                d.getInitialBalance(),
                                d.getUpperBound()
                        )
                );
            }
            return deposits;
        } catch (FileNotFoundException e) {
            throw new GenericExceptions("Config File not Found", e);
        }
    }
}
