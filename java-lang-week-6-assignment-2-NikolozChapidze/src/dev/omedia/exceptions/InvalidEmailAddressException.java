package dev.omedia.exceptions;

import dev.omedia.notifications.NotificationType;

public class InvalidEmailAddressException extends InvalidAddressException{
    public InvalidEmailAddressException(int id, String receiver) {
        super(id, NotificationType.EMAIL,receiver);
    }
}
