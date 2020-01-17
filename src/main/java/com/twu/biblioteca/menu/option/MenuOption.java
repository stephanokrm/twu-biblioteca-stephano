package com.twu.biblioteca.menu.option;

public class MenuOption {
    protected int number;
    protected String label;

    public MenuOption(int number, String label) {
        this.number = number;
        this.label = label;
    }

    @Override
    public String toString() {
        return String.format("%d. %s", number, label);
    }
}
