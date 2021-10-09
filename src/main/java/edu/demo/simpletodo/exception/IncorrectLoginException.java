package edu.demo.simpletodo.exception;

public class IncorrectLoginException extends Exception {
    public IncorrectLoginException() {
    }

    public IncorrectLoginException(String message) {
        super(message);
    }
}
