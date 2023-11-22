package dev.omedia;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class MiddleMan implements Runnable{
    public static HashMap<String, ArrayList<MiddleMan>> membersConnectors;
    public static HashMap<String, StringBuffer> chatHistory;
    private String userName;
    private String groupName;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Socket socket;

    static{
        membersConnectors = new HashMap<>();
        chatHistory       = new HashMap<>();
    }

    public MiddleMan(Socket socket) {
        try {
            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            init();
            membersConnectors.get(groupName).add(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getGroupName() {
        return groupName;
    }

    private void init() {
        try {
            userName = reader.readLine();
            groupName = reader.readLine();

            if(!membersConnectors.containsKey(groupName)){
                membersConnectors.put(groupName,new ArrayList<>());
            }

            writeToClient("want to load older messages if exists?(yes or no) ");
            String answ = reader.readLine();
            boolean load = answ.equalsIgnoreCase("yes");
            if(load){
                if (chatHistory.containsKey(groupName)){
                    writeToClient(chatHistory.get(groupName).toString());
                }else{
                    writeToClient("No history found! :(");
                }
            }
            if(!chatHistory.containsKey(groupName)){
                chatHistory.put(groupName,new StringBuffer());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToClient(String message){
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            closeConnections();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (socket.isConnected()){
            try {
                String message = reader.readLine();
                if (message.length()!=0) {
                    sendMessage(String.format("%s: %s", userName, message));
                }
            }catch (IOException e){
                closeConnections();
                throw new RuntimeException(e);
            }
        }
    }

    private void sendMessage(String message) {
        chatHistory.get(groupName).append(message).append(System.lineSeparator());
        for(final var member : membersConnectors.get(groupName)){
            if(!member.userName.equals(this.userName)) {
                try {
                    member.writer.write(message);
                    member.writer.newLine();
                    member.writer.flush();
                } catch (IOException e) {
                    closeConnections();
                    throw new RuntimeException(e);
                }
            }
        }
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
