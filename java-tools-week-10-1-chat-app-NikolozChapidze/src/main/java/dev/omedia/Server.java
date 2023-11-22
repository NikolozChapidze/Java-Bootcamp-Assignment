package dev.omedia;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }

    private ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(Config.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (!serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                MiddleMan middleMan = new MiddleMan(socket);
                System.out.printf("%s joined in %s group;%n"
                        , middleMan.getUserName()
                        , middleMan.getGroupName());
                Thread worker = new Thread(middleMan);
                worker.start();
            } catch (IOException e) {
                stop();
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
