package edu.demo.simpletodo.exception;

public class UsernameIsAlreadyExistException extends Exception {
    public UsernameIsAlreadyExistException() {
    }

    public UsernameIsAlreadyExistException(String message) {
        super(message);
    }
}
