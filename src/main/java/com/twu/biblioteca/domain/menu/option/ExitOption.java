package com.twu.biblioteca.domain.menu.option;

import java.io.PrintStream;

public class ExitOption extends MenuOption {
    public static final int NUMBER = 0;
    public static final String LABEL = "Exit";

    public ExitOption(PrintStream out) {
        super(NUMBER, LABEL, out);
    }

    @Override
    public void show() {
        out.println("Closing the application...");
    }
}
