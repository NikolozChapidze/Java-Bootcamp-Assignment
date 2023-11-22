package dev.omedia;

import dev.omedia.notifications.EmailNotification;
import dev.omedia.notifications.Notification;
import dev.omedia.notifications.NotificationType;
import dev.omedia.notifications.SMSNotification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

import static dev.omedia.notifications.NotificationType.*;

public class Main {

    private static final String notificationsFile = "src/resources/notification.csv";

    public static void main(String[] args) {
        Collection<Notification> notifications = new ArrayList<>();
        readNotifications(notifications);
        sendNotifications(notifications);
    }

    private static void readNotifications(Collection<Notification> notifications) {
        try {
            Files.readAllLines(Path.of(notificationsFile))
                    .stream()
                    .skip(1)
                    .map(line -> line.split(","))
                    .map(arguments -> {switch(NotificationType.valueOf(arguments[1])){
                        case EMAIL : return generateEmailNotification(arguments);
                        case SMS : return generateSMSNotification(arguments);
                        default: return null;
                    }
                    })
                    .forEach(notifications::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static EmailNotification generateEmailNotification(String[] arguments) {
        return new EmailNotification(Integer.parseInt(arguments[0]),arguments[2],arguments[3],arguments[4]);
    }

    private static SMSNotification generateSMSNotification(String[] arguments){
        return new SMSNotification(Integer.parseInt(arguments[0]),arguments[2],arguments[3]);
    }

    private static void sendNotifications(Collection<Notification> notifications) {
        notifications.forEach(Notification::send);
    }
}
