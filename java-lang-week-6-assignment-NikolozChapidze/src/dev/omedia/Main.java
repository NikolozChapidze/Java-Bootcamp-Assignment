package dev.omedia;

public class Main {

    public static void main(String[] args) {
        BorderCheckpoint checkpoint = new BorderCheckpoint();
        checkpoint.readPeopleData();
        checkpoint.readCrossingData();
        checkpoint.generateStatistics();
    }
}
