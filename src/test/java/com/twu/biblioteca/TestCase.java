package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;

import java.io.InputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;

public abstract class TestCase {
    protected PrintStream out;
    protected InputStream in;

    private PrintStream originalOut;
    private InputStream originalIn;

    @Before
    public void setUp() {
        originalIn = System.in;
        originalOut = System.out;

        in = mock(InputStream.class);
        out = mock(PrintStream.class);

        System.setIn(in);
        System.setOut(out);
    }

    @After
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}
