package dev.omedia.exceptions;

import dev.omedia.notifications.NotificationType;

public class InvalidAddressException extends Exception {
    public InvalidAddressException(int id, NotificationType type, String receiver) {
        super(String.format("%d: INVALID %s ADDRESS: %s",id,type,receiver));
    }
}
