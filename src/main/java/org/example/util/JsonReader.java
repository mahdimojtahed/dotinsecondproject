package org.example.util;

import com.google.gson.Gson;
import org.example.exeptionHandling.GenericExceptions;
import org.example.resources.Strings;
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
    static Gson gson = new Gson();
    static ArrayList<Deposit> deposits = new ArrayList<>();

    public static ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    static public void readJSON(String jsonPath) throws GenericExceptions {
        JsonContent jsonContent;

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

        } catch (FileNotFoundException e) {
            throw new GenericExceptions(Strings.SERVER_CONFIG_FILE, e);
        }
    }
}
