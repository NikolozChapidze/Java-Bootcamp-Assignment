package dev.omedia.exceptions;

public class EmailSubjectException extends Exception{
    public EmailSubjectException(int id) {
        super(String.format("%d: EMAIL WITHOUT SUBJECT",id));
    }
}
