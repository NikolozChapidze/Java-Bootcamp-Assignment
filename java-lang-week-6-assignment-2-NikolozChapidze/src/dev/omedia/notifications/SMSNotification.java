package dev.omedia.notifications;

import dev.omedia.Logger;
import dev.omedia.exceptions.InvalidSmsAddressException;
import dev.omedia.exceptions.NotificationSendException;

import java.util.Random;

public class SMSNotification extends Notification {
    private Random random = new Random();
    private final String mnumberRegex = "^\\d{9}$";

    public SMSNotification(int id, String receiver, String text) {
        super(id, receiver, text);
    }

    @Override
    public void send() {
        try {
            checkReceiver();
            if(random.nextBoolean()){
                System.out.printf("%s წარმატებით გაიგზანა %s-სთან%n", NotificationType.SMS, getReceiver());
            }else{
                throw new NotificationSendException(getId());
            }
        } catch (InvalidSmsAddressException | NotificationSendException e) {
            Logger.logError(e.getMessage());
        }
    }

    @Override
    public void checkReceiver() throws InvalidSmsAddressException {
        if (!getReceiver().matches(mnumberRegex)) {
            throw new InvalidSmsAddressException(getId(),getReceiver());
        }
    }
}
