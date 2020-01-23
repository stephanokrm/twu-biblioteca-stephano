package com.twu.biblioteca.foundation;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.domain.Welcome;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ApplicationTest extends TestCase {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private Application application;
    private Question question;

    @Override
    public void setUp() {
        super.setUp();

        question = mock(Question.class);
        application = new Application(question, out);
    }

    @Test
    public void hasCurrentVersion() {
        assertThat(application.version(), is(equalTo("1.7")));
    }

    @Test
    public void run() {
        when(question.askForInteger("Enter an option: ")).thenReturn(0);

        application.run();

        verify(out).println(Welcome.MESSAGE);
    }

    @Test
    public void exitWhenInputZero() {
        when(question.askForInteger("Enter an option: ")).thenReturn(0);

        application.run();

        assertThat(application.isRunning(), is(false));
    }
}
