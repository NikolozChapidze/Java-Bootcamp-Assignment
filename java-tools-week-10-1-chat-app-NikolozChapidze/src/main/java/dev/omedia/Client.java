package dev.omedia;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String username = scanner.nextLine();
        System.out.print("Enter group name you want to join or create: ");
        String groupname = scanner.nextLine();

        Client client = new Client(new Socket(Config.host, Config.port), username, groupname);
        client.start();
    }
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String userName;
    private String groupName;

    public Client(Socket socket, String userName, String groupName) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName = userName;
            this.groupName = groupName;

            writer.write(userName);
            writer.newLine();
            writer.flush();

            writer.write(groupName);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            closeConnections();
            e.printStackTrace();
        }
    }

    public void start(){
        Thread senderThread = sender();
        Thread receiverThread = receiver();
        receiverThread.start();
        senderThread.start();
    }

    private Thread receiver(){
        return new Thread(()->{
            while (socket.isConnected()){
                try {
                    String message = reader.readLine();
                    System.out.printf("%s\n",message);
                } catch (IOException e) {
                    closeConnections();
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private Thread sender(){
        return new Thread(()->{
            try {
                Scanner scanner = new Scanner(System.in);
                writer.write(scanner.nextLine());
                writer.newLine();
                writer.flush();
                while (socket.isConnected()) {
                    String message = scanner.nextLine();
                    writer.write( message);
                    writer.newLine();
                    writer.flush();

                }
            } catch (IOException e) {
                closeConnections();
                throw new RuntimeException(e);
            }
        });
    }

    private void closeConnections() {
        try {
            socket.close();
            reader.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
