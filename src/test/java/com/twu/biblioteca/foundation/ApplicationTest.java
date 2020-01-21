package com.twu.biblioteca.foundation;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.domain.Welcome;
import com.twu.biblioteca.exception.InvalidMenuOptionException;
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
        assertThat(application.version(), is(equalTo("1.4")));
    }

    @Test
    public void abort() throws RuntimeException {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Runtime Error");

        application.abort("Runtime Error");
    }

    @Test
    public void boot() {
        application.boot();

        assertThat(application.isBooted(), is(true));
    }

    @Test
    public void run() throws InvalidMenuOptionException {
        when(question.askForInteger("Enter an option: ")).thenReturn(1);

        application.run();

        verify(out).println(Welcome.MESSAGE);
    }
}
