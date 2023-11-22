package dev.omedia.exceptions;

import dev.omedia.notifications.NotificationType;

public class InvalidSmsAddressException extends InvalidAddressException{
    public InvalidSmsAddressException(int id, String receiver) {
        super(id, NotificationType.SMS,receiver);
    }
}
