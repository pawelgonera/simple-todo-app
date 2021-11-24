package edu.demo.simpletodo.exception;

public class NotTheSamePasswordException extends Exception {
    public NotTheSamePasswordException() {
    }

    public NotTheSamePasswordException(String message) {
        super(message);
    }
}
