package org.example.server.config;

public class Configuration {
    static private int port;
    static private String outLog;
    public static String getOutLog() {
        return outLog;
    }

    public static void setOutLog(String outLog) {
        Configuration.outLog = outLog;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Configuration.port = port;
    }

}
