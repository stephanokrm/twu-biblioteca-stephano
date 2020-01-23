package com.twu.biblioteca.exception;

public class BookNotAvailableException extends Exception {
    public static final String MESSAGE = "Sorry, that book is not available";

    public BookNotAvailableException() {
        super(MESSAGE);
    }
}
