package com.twu.biblioteca.menu;

import com.twu.biblioteca.menu.option.MenuOption;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MenuTest {
    private Menu menu;
    private PrintStream printStream;
    private List<MenuOption> menuOptions;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        menuOptions = new ArrayList<>();
        menu = new Menu(printStream, menuOptions);
    }

    @Test
    public void shouldShowMenuOptions() {
        MenuOption menuOption = new MenuOption(1, "Option");

        menuOptions.add(menuOption);

        menu.show();

        verify(printStream).println("1. Option");
    }
}
