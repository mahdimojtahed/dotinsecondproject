package org.example.util;

import com.google.gson.Gson;
import org.example.exeptionHandling.GenericExceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonReader {
    Gson gson = new Gson();
    Object object;
    public int readJSON(String jsonPath) throws GenericExceptions {
        try {
            object = gson.fromJson(new FileReader(jsonPath), Object.class);
            int port = Integer.parseInt(object.toString().substring(6,10));
            return port;
        } catch (FileNotFoundException e) {
            throw new GenericExceptions("Config File not Found",e);
        }
    }
}
