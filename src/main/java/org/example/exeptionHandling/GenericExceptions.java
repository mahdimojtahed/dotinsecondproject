package org.example.exeptionHandling;

public class GenericExceptions extends Throwable{
    public GenericExceptions(String message) {
        super(message);
    }

    public GenericExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
