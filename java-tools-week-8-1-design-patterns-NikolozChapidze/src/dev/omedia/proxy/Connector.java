package dev.omedia.proxy;

public class Connector implements  IConnection {

    @Override
    public void connect(String username, String database) {
        System.out.printf("%s connected to %s.",username,database);
    }
}
