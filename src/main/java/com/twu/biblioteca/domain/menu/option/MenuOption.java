package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Console;

public abstract class MenuOption {
    private final int number;
    private final String label;
    private final boolean guarded;
    protected final Console console;

    public MenuOption(int number, String label, Console console) {
        this(number, label, console, false);
    }

    public MenuOption(int number, String label, Console console, boolean guarded) {
        this.number = number;
        this.label = label;
        this.console = console;
        this.guarded = guarded;
    }

    public int getNumber() {
        return number;
    }

    public boolean isGuarded() {
        return guarded;
    }

    public void open() throws Exception {
        console.doWrite(label);

        show();
    }

    public abstract void show() throws Exception;

    @Override
    public String toString() {
        return String.format("%d. %s", number, label);
    }
}
