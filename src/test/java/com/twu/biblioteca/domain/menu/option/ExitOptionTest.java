package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.domain.menu.Menu;
import org.junit.Test;

import static org.mockito.Mockito.verify;

public class ExitOptionTest extends TestCase {
    private Menu menu;
    private ExitOption exitOption;

    @Override
    public void setUp() {
        super.setUp();

        exitOption = new ExitOption(out);
        menu = new Menu(out);
        menu.addOption(exitOption);
    }

    @Test
    public void showExitMessage() {
        exitOption.show();

        verify(out).println("Closing the application...");
    }

    @Test
    public void showExitOption() {
        menu.open();

        verify(out).println("0. Exit");
    }
}
