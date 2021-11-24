package edu.demo.simpletodo.exception;

public class TooShortPasswordLengthException extends Exception {
    public TooShortPasswordLengthException() {
    }

    public TooShortPasswordLengthException(String message) {
        super(message);
    }
}
