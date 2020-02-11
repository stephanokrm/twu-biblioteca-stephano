package com.twu.biblioteca.domain.menu;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.option.LoginOption;
import com.twu.biblioteca.exception.InvalidMenuOptionException;
import com.twu.biblioteca.model.MenuOptionStub;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class MenuTest extends InteractsWithConsole {
    private Menu menu;
    @Mock
    UserRepository userRepository;
    private AuthService authService;

    @Override
    public void setUp() {
        super.setUp();

        UserService userService = new UserService(userRepository);

        authService = new AuthService(userService);

        menu = new Menu(console, authService);
    }

    @Test
    public void openMenu() {
        menu.open();

        verify(console).doWrite("Menu");
    }

    @Test(expected = InvalidMenuOptionException.class)
    public void notifyWhenChooseAnInvalidOption() throws Exception {
        menu.run(-1);
    }

    @Test
    public void askForCredentialsWhenRunGuardedMenuOption() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("0", "0", "Name", "Email", "Phone"));

        doReturn(users).when(userRepository).all();
        doReturn("0").when(console).askQuestion("Enter your library number: ");
        doReturn("0").when(console).askQuestion("Enter your password: ");

        MenuOptionStub.GUARDED = true;
        MenuOptionStub menuOptionStub = new MenuOptionStub(console);
        LoginOption loginOption = new LoginOption(console, authService);

        menu.addOption(loginOption);
        menu.addOption(menuOptionStub);
        menu.run(MenuOptionStub.NUMBER);

        verify(console).doWrite("Login");
        verify(console).askQuestion("Enter your library number: ");
        verify(console).askQuestion("Enter your password: ");
    }

    @Test
    public void getMenuByOption() throws InvalidMenuOptionException {
        MenuOptionStub menuOptionStub = new MenuOptionStub(console);
        menu.addOption(menuOptionStub);

        assertThat(menu.getMenuByOption(MenuOptionStub.NUMBER), is(equalTo(menuOptionStub)));
    }
}
