package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.io.InputStream;
import java.io.PrintStream;

@RunWith(MockitoJUnitRunner.class)
public abstract class TestCase {
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Mock
    protected PrintStream out;

    @Mock
    protected InputStream in;

    private PrintStream originalOut;
    private InputStream originalIn;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        originalIn = System.in;
        originalOut = System.out;

        System.setIn(in);
        System.setOut(out);
    }

    @After
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}
