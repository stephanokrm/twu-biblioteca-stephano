package com.twu.biblioteca.domain.menu;

import com.twu.biblioteca.domain.menu.option.MenuOption;
import com.twu.biblioteca.exception.InvalidMenuOptionException;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private PrintStream out;
    private List<MenuOption> options = new ArrayList<>();

    public Menu(PrintStream out) {
        this.out = out;
    }

    public void open() {
        out.println("- Menu\n");
        options.stream()
                .map(MenuOption::toString)
                .forEach(option -> out.println(option));
    }

    public void run(int option) throws InvalidMenuOptionException {
        options.stream()
                .filter(menuOption -> menuOption.getNumber() == option)
                .findFirst()
                .orElseThrow(InvalidMenuOptionException::new)
                .show();
    }

    public void addOption(MenuOption menuOption) {
        this.options.add(menuOption);
    }
}
