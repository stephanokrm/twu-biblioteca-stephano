package com.twu.biblioteca.domain.menu;

import com.twu.biblioteca.domain.menu.option.MenuOption;
import com.twu.biblioteca.exception.InvalidMenuOptionException;
import com.twu.biblioteca.service.UserService;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final PrintStream out;
    private final UserService userService;
    private List<MenuOption> options = new ArrayList<>();

    public Menu(PrintStream out, UserService userService) {
        this.out = out;
        this.userService = userService;
    }

    public void open() {
        out.println("- Menu\n");
        options.stream()
                .map(MenuOption::toString)
                .forEach(out::println);
    }

    public void run(int option) throws Exception {
        MenuOption menuOption = getMenuByOption(option);
        menuOption.show();
    }

    public void addOption(MenuOption menuOption) {
        this.options.add(menuOption);
    }

    public MenuOption getMenuByOption(int option) throws InvalidMenuOptionException {
        return options.stream()
                .filter(menuOption -> menuOption.getNumber() == option)
                .findFirst()
                .orElseThrow(InvalidMenuOptionException::new);
    }
}
