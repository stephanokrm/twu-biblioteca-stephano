package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.service.AuthService;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class LoginTest extends InteractsWithConsole {
    private LoginOption loginOption;

    @Mock
    private AuthService authService;

    @Override
    public void setUp() {
        super.setUp();

        loginOption = new LoginOption(console, authService);
    }

    @Test
    public void showLoginOption() {
        Menu menu = new Menu(console, authService);
        menu.addOption(loginOption);
        menu.open();

        verify(console).doWrite("6. Login");
    }

    @Test
    public void runLogin() throws Exception {
        expectsOutput("Login")
                .expectsQuestion("Enter your library number: ", "Library Number")
                .expectsQuestion("Enter your password: ", "Password")
                .execute(loginOption);
    }
}
