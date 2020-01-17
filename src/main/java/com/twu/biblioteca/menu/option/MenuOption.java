package com.twu.biblioteca.menu.option;

public class MenuOption {
    private int number;
    private String label;

    public MenuOption(int number, String label) {
        this.number = number;
        this.label = label;
    }

    @Override
    public String toString() {
        return String.format("%d. %s", number, label);
    }
}
