package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.foundation.Question;
import com.twu.biblioteca.service.AuthService;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LoginTest extends TestCase {
    private Menu menu;
    private Question question;
    private AuthService authService;

    @Override
    public void setUp() {
        super.setUp();

        question = mock(Question.class);

        authService = mock(AuthService.class);
        LoginOption loginOption = new LoginOption(out, question, authService);

        menu = new Menu(out, authService);
        menu.addOption(loginOption);
    }

    @Test
    public void showLoginOption() {
        menu.open();

        verify(out).println("6. Login");
    }

    @Test
    public void askForLibraryNumber() throws Exception {
        doNothing().when(authService).authenticate("Library Number", "Password");
        when(question.askForString("Enter your library number: ")).thenReturn("Library Number");

        menu.run(LoginOption.NUMBER);

        verify(question).askForString("Enter your library number: ");
    }

    @Test
    public void askForPassword() throws Exception {
        doNothing().when(authService).authenticate("Library Number", "Password");
        when(question.askForString("Enter your password: ")).thenReturn("Password");

        menu.run(LoginOption.NUMBER);

        verify(question).askForString("Enter your password: ");
    }
}
