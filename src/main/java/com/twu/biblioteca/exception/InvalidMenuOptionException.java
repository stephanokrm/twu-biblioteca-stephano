package com.twu.biblioteca.exception;

public class InvalidMenuOptionException extends Exception {
    public static final String MESSAGE = "Please select a valid option!";

    public InvalidMenuOptionException() {
        super(MESSAGE);
    }
}
