package org.example.exeptionHandling;

public class HttpExceptions extends Throwable{
    public HttpExceptions(String message) {
        super(message);
    }

    public HttpExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
