package dev.omedia.exceptions;

public class PersonNameFormatException extends FormatException{
    public PersonNameFormatException(String message) {
        super(String.format("%s is incorrect name format.",message));
    }
}
