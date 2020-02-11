package com.twu.biblioteca;

import com.twu.biblioteca.domain.menu.option.MenuOption;
import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.foundation.PendingMenuOption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;

public abstract class InteractsWithConsole extends TestCase {
    protected Console console;

    public List<String> expectedOutput = new ArrayList<>();
    public Map<String, String> expectedQuestions = new HashMap<>();

    @Override
    public void setUp() {
        super.setUp();

        console = mock(Console.class);
    }

    public PendingMenuOption option(MenuOption option) {
        return new PendingMenuOption(this, console, option);
    }
}
