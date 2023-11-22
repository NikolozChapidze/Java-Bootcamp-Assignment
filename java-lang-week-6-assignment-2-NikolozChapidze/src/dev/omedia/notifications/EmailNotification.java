package dev.omedia.notifications;

import dev.omedia.Logger;
import dev.omedia.exceptions.EmailSubjectException;
import dev.omedia.exceptions.InvalidEmailAddressException;
import dev.omedia.exceptions.NotificationSendException;

import java.util.Random;

public class EmailNotification extends Notification{
    private Random random = new Random();
    private static final String addressRegex = "[a-z1-9]{2,}[@][a-z1-9]{2,}[.][a-z]{2,3}";
    private static final String subjectRegex = "^[\\s\0]*$";
    private String subject;

    public EmailNotification(int id, String receiver, String subject, String text) {
        super(id, receiver, text);
        this.subject = subject;
    }

    @Override
    public void send() {
        try {
            checkReceiver();
            checkSubject();
            if(random.nextBoolean()){
                System.out.printf("%s წარმატებით გაიგზანა %s-სთან%n", NotificationType.EMAIL, getReceiver());
            }else{
                throw new NotificationSendException(getId());
            }
        } catch (InvalidEmailAddressException | EmailSubjectException | NotificationSendException e) {
            Logger.logError(e.getMessage());
        }
    }

    private void checkSubject() throws EmailSubjectException {
        if(subject == null || subject.matches(subjectRegex)){
            throw new EmailSubjectException(getId());
        }
    }

    @Override
    public void checkReceiver() throws InvalidEmailAddressException {
        if(!getReceiver().matches(addressRegex)){
            throw new InvalidEmailAddressException(getId(),getReceiver());
        }
    }
}
