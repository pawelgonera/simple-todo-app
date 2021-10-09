package edu.demo.simpletodo.exception;

public class NotValidUserEmailException extends Exception {
    public NotValidUserEmailException() {
    }

    public NotValidUserEmailException(String message) {
        super(message);
    }
}
