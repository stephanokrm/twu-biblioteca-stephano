package com.twu.biblioteca.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BookNotAvailableExceptionTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void seeMessageWhenThrow() throws BookNotAvailableException {
        expectedException.expectMessage("Sorry, that book is not available");

        throw new BookNotAvailableException();
    }
}
