package com.twu.biblioteca.exception;

public class InvalidBookException extends Exception {
    public static final String MESSAGE = "That is not a valid book to return.";

    public InvalidBookException() {
        super(MESSAGE);
    }
}
