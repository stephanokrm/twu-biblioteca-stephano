package com.twu.biblioteca.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InvalidMenuOptionExceptionTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void seeMessageWhenThrow() throws InvalidMenuOptionException {
        expectedException.expectMessage("Please select a valid option!");

        throw new InvalidMenuOptionException();
    }
}
