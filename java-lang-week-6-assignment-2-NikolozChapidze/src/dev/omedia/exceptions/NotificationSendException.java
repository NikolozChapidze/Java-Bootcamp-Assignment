package dev.omedia.exceptions;

public class NotificationSendException extends RuntimeException{
    public NotificationSendException(int id) {
        super(String.format("%d: SEND FAILED",id));
    }
}
