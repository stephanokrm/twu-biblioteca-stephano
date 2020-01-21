package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;

public abstract class TestCase {
    protected PrintStream out;
    protected PrintStream originalOut;
    protected InputStream in;
    protected InputStream originalIn;

    @Before
    public void setUp() {
        originalIn = System.in;
        in = mock(BufferedInputStream.class);
        System.setIn(in);

        originalOut = System.out;
        out = mock(PrintStream.class);
        System.setOut(out);
    }

    @After
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}
