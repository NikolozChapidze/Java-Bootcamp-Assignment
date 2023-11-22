package dev.omedia.notifications;

import dev.omedia.exceptions.InvalidEmailAddressException;
import dev.omedia.exceptions.InvalidSmsAddressException;

public abstract class Notification {
    private int id;
    private String receiver;
    private String text;

    public Notification(int id, String receiver, String text) {
        this.id = id;
        this.receiver = receiver;
        this.text = text;
    }

    abstract public void send();

    abstract public void checkReceiver() throws InvalidEmailAddressException, InvalidSmsAddressException;

    public int getId() {
        return id;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }
}
