package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ExitOptionTest extends InteractsWithConsole {
    private Menu menu;
    private ExitOption exitOption;

    @Override
    public void setUp() {
        super.setUp();

        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        exitOption = new ExitOption(console);

        menu = new Menu(console, new AuthService(userService));
        menu.addOption(exitOption);
    }

    @Test
    public void showExitOption() {
        menu.open();

        verify(console).doWrite("0. Exit");
    }

    @Test
    public void runExitOption() throws Exception {
        option(exitOption)
                .expectsOutput("Exit")
                .execute();
    }
}
