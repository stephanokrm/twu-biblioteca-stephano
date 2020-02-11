package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class LoginTest extends InteractsWithConsole {
    private List<User> users;
    private LoginOption loginOption;
    private AuthService authService;

    @Override
    public void setUp() {
        super.setUp();

        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        users = new ArrayList<>();
        authService = new AuthService(userService);

        loginOption = new LoginOption(console, authService);

        doReturn(users).when(userRepository).all();
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
        User user = new User("0", "0", "Name", "Email", "Phone");
        users.add(user);

        option(loginOption)
                .expectsOutput("Login")
                .expectsQuestion("Enter your library number: ", "0")
                .expectsQuestion("Enter your password: ", "0")
                .execute();
    }
}
