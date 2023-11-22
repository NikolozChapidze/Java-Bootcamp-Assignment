package dev.omedia.proxy;

import java.util.Random;

public class ConnectionProxy implements IConnection{
    Connector connector ;
    Random random = new Random();

    public ConnectionProxy() {
        this.connector = new Connector();
    }

    @Override
    public void connect(String username, String database) {
        if(checkAccessibility(username,database)){
            connector.connect(username,database);
        }else {
            System.out.printf("Connection to %s is not permitted for %s", database,username);
        }
    }

    private boolean checkAccessibility(String username, String database) {
        return random.nextBoolean();
    }
}
