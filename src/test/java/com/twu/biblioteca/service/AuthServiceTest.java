package com.twu.biblioteca.service;

import com.twu.biblioteca.exception.AuthenticationException;
import com.twu.biblioteca.model.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthServiceTest {
    private AuthService authService;
    private UserService userService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        userService = mock(UserService.class);
        authService = new AuthService(userService);
    }

    @Test
    public void checkIfIsAuthenticated() throws AuthenticationException {
        User user = new User("0", "0", "Name", "email@gmail.com", "(00) 00000-0000");

        when(userService.getUserByLibraryNumber("0")).thenReturn(user);

        authService.authenticate("0", "0");

        assertThat(authService.isAuthenticated(), is(true));
    }

    @Test
    public void checkIfIsNotAuthenticated() throws AuthenticationException {
        User user = new User("0", "0", "Name", "email@gmail.com", "(00) 00000-0000");

        when(userService.getUserByLibraryNumber("0")).thenReturn(user);

        authService.authenticate("0", "0");

        assertThat(authService.isNotAuthenticated(), is(false));
    }

    @Test
    public void doNotLoginWithInvalidLibraryNumber() throws AuthenticationException {
        expectedException.expect(AuthenticationException.class);
        expectedException.expectMessage("These credentials do not match our records.");

        User user = new User("0", "0", "Name", "email@gmail.com", "(00) 00000-0000");

        when(userService.getUserByLibraryNumber("0")).thenReturn(user);

        authService.authenticate("000", "0");
    }

    @Test
    public void doNotLoginWithInvalidPassword() throws AuthenticationException {
        expectedException.expect(AuthenticationException.class);
        expectedException.expectMessage("These credentials do not match our records.");

        User user = new User("0", "0", "Name", "email@gmail.com", "(00) 00000-0000");

        when(userService.getUserByLibraryNumber("0")).thenReturn(user);

        authService.authenticate("0", "000");
    }

    @Test
    public void canAccessAuthenticatedUserAfterLogin() throws AuthenticationException {
        User user = new User("0", "0", "Name", "email@gmail.com", "(00) 00000-0000");

        when(userService.getUserByLibraryNumber("0")).thenReturn(user);

        authService.authenticate("0", "0");

        assertThat(authService.getUser(), is(equalTo(user)));
    }
}
