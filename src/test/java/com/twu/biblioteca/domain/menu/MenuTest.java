package com.twu.biblioteca.domain.menu;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.exception.InvalidMenuOptionException;
import com.twu.biblioteca.model.MenuOptionStub;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MenuTest extends TestCase {
    private Menu menu;

    @Override
    public void setUp() {
        super.setUp();

        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        menu = new Menu(out, new AuthService(userService));
    }

    @Test
    public void openMenu() {
        menu.open();

        verify(out).println("\nMenu\n");
    }

    @Test(expected = InvalidMenuOptionException.class)
    public void notifyWhenChooseAnInvalidOption() throws Exception {
        menu.run(-1);
    }

    @Test
    public void getMenuByOption() throws InvalidMenuOptionException {
        MenuOptionStub menuOptionStub = new MenuOptionStub(out);
        menu.addOption(menuOptionStub);

        assertThat(menu.getMenuByOption(MenuOptionStub.NUMBER), is(equalTo(menuOptionStub)));
    }
}
