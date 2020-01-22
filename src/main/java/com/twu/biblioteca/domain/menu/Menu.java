package com.twu.biblioteca.domain.menu;

import com.twu.biblioteca.domain.menu.option.CheckoutABookOption;
import com.twu.biblioteca.domain.menu.option.ExitOption;
import com.twu.biblioteca.domain.menu.option.ListOfBooksOption;
import com.twu.biblioteca.domain.menu.option.MenuOption;
import com.twu.biblioteca.exception.InvalidMenuOptionException;
import com.twu.biblioteca.repository.BookRepository;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private PrintStream out;
    private boolean booted = false;
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

    public boolean isBooted() {
        return booted;
    }

    public void boot() {
        options.add(new ListOfBooksOption(out, new BookRepository()));
        options.add(new CheckoutABookOption());
        options.add(new ExitOption(out));
        booted = true;
    }

    public boolean hasOption(int option) {
        return options.stream().anyMatch(menuOption -> menuOption.getNumber() == option);
    }

    public void addOption(MenuOption menuOption) {
        this.options.add(menuOption);
    }
}
