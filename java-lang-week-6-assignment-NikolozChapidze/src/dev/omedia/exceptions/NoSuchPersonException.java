package dev.omedia.exceptions;

public class NoSuchPersonException extends FormatException{

    public NoSuchPersonException(String message) {
        super(String.format("%s is not in database.", message));
    }
}
