package com.twu.biblioteca.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InvalidBookExceptionTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void seeMessageWhenThrow() throws InvalidBookException {
        expectedException.expectMessage("That is not a valid book to return.");

        throw new InvalidBookException();
    }
}
