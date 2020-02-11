package com.twu.biblioteca.domain.menu;

import com.twu.biblioteca.domain.menu.option.LoginOption;
import com.twu.biblioteca.domain.menu.option.MenuOption;
import com.twu.biblioteca.exception.InvalidMenuOptionException;
import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.service.AuthService;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final Console console;
    private final AuthService authService;
    private List<MenuOption> options = new ArrayList<>();

    public Menu(Console console, AuthService authService) {
        this.console = console;
        this.authService = authService;
    }

    public void open() {
        console.doWrite("Menu");

        if (authService.isAuthenticated()) {
            console.doWrite(String.format("Logged in as %s", authService.getUser().getLibraryNumber()));
        }

        options.forEach(option -> console.doWrite(option.toString()));
    }

    public void run(int option) throws Exception {
        MenuOption menuOption = getMenuByOption(option);

        if (menuOption.isGuarded() && authService.isNotAuthenticated()) {
            run(LoginOption.NUMBER);
        }

        menuOption.open();
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
