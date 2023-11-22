package dev.omedia.exceptions;

public class PersonDocumentNumberFormatException extends FormatException{
    public PersonDocumentNumberFormatException(String message) {
        super(String.format("%s is incorrect document number format.", message));
    }
}
