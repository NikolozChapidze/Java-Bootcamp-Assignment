package dev.omedia.exceptions;

public class CrossTypeFormatException extends FormatException{

    public CrossTypeFormatException(String message) {
        super(String.format("%s is incorrect cross type format.",message));
    }
}
