package edu.demo.simpletodo.exception;

public class TooShortUsernameLengthException extends Exception {
    public TooShortUsernameLengthException() {
    }

    public TooShortUsernameLengthException(String message) {
        super(message);
    }
}
