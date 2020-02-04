package com.twu.biblioteca.domain.menu;

import com.twu.biblioteca.domain.menu.option.LoginOption;
import com.twu.biblioteca.domain.menu.option.MenuOption;
import com.twu.biblioteca.exception.InvalidMenuOptionException;
import com.twu.biblioteca.service.AuthService;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final PrintStream out;
    private final AuthService authService;
    private List<MenuOption> options = new ArrayList<>();

    public Menu(PrintStream out, AuthService authService) {
        this.out = out;
        this.authService = authService;
    }

    public void open() {
        out.println("- Menu\n");
        options.stream()
                .map(MenuOption::toString)
                .forEach(out::println);
    }

    public void run(int option) throws Exception {
        MenuOption menuOption = getMenuByOption(option);

        if (menuOption.isGuarded() && authService.isNotAuthenticated()) {
            run(LoginOption.NUMBER);
        }

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
