package com.twu.biblioteca.foundation;

import com.twu.biblioteca.InteractsWithConsole;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class ApplicationTest extends InteractsWithConsole {
    @Test
    public void exitWhenInputZero() {
        when(console.askQuestion("Enter an option: ")).thenReturn("0");

        Application application = new Application(console);
        application.run();

        assertThat(application.isRunning(), is(false));
    }
}
