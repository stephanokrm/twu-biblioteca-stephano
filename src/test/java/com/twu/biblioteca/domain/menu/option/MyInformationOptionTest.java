package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MyInformationOptionTest extends InteractsWithConsole {
    private Menu menu;
    private MyInformationOption myInformationOption;

    @Override
    public void setUp() {
        super.setUp();

        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);
        AuthService authService = new AuthService(userService);

        authService.actingAs(new User("0", "0", "Name", "email@gmail.com", "(00) 00000-0000"));
        myInformationOption = new MyInformationOption(console, authService);

        menu = new Menu(console, authService);
        menu.addOption(myInformationOption);
    }

    @Test
    public void showMyInformationOption() {
        menu.open();

        verify(console).doWrite("8. My Information");
    }

    @Test
    public void runMyInformationOption() throws Exception {
        option(myInformationOption)
                .expectsOutput("My Information")
                .expectsOutput("Name: Name | Email: email@gmail.com | Phone Number: (00) 00000-0000")
                .execute();
    }
}
