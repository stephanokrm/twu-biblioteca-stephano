package com.twu.biblioteca.menu;

import com.twu.biblioteca.menu.option.MenuOption;

import java.io.PrintStream;
import java.util.List;

public class Menu {
    private PrintStream printStream;
    private List<MenuOption> options;

    public Menu(PrintStream printStream, List<MenuOption> options) {
        this.printStream = printStream;
        this.options = options;
    }

    public void open() {
        printStream.println("- Menu");
        options.forEach(menuOption -> printStream.println(menuOption.toString()));
    }

    public void run(int option) {
        options.stream()
                .filter(menuOption -> menuOption.getNumber() == option)
                .findFirst()
                .ifPresent(MenuOption::show);
    }
}
