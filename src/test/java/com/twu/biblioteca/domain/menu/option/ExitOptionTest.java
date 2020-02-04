package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ExitOptionTest extends TestCase {
    private Menu menu;
    private ExitOption exitOption;

    @Override
    public void setUp() {
        super.setUp();

        exitOption = new ExitOption(out);

        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        menu = new Menu(out, new AuthService(userService));
        menu.addOption(exitOption);
    }

    @Test
    public void showExitMessage() {
        exitOption.show();

        verify(out).println("Closing the application...");
    }

    @Test
    public void showExitOption() {
        menu.open();

        verify(out).println("0. Exit");
    }
}
