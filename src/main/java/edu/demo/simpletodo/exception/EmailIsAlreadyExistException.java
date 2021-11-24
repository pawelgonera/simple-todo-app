package edu.demo.simpletodo.exception;

public class EmailIsAlreadyExistException extends Exception
{
    public EmailIsAlreadyExistException() {
    }

    public EmailIsAlreadyExistException(String message) {
        super(message);
    }
}
