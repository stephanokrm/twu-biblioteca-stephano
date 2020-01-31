package com.twu.biblioteca.service;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.UserRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest extends TestCase {
    private UserRepository userRepository;
    private UserService userService;

    @Override
    public void setUp() {
        super.setUp();

        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void login() {
        List<User> users = new ArrayList<>();
        users.add(new User("0", "0"));

        when(userRepository.all()).thenReturn(users);

        boolean authenticated = userService.login("0", "0");

        assertThat(authenticated, is(true));
    }

    @Test
    public void doNotLoginWithInvalidLibraryNumber() {
        List<User> users = new ArrayList<>();
        users.add(new User("0", "0"));

        when(userRepository.all()).thenReturn(users);

        boolean authenticated = userService.login("000", "0");

        assertThat(authenticated, is(false));
    }

    @Test
    public void doNotLoginWithInvalidPassword() {
        List<User> users = new ArrayList<>();
        users.add(new User("0", "0"));

        when(userRepository.all()).thenReturn(users);

        boolean authenticated = userService.login("0", "000");

        assertThat(authenticated, is(false));
    }

    @Test
    public void getUserByLibraryNumber() {
        User user = new User("0", "0");

        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.all()).thenReturn(users);

        assertThat(userService.getUserByLibraryNumber("0"), is(equalTo(user)));
    }

    @Test
    public void canAccessAuthenticatedUserAfterLogin() {
        User user = new User("0", "0");

        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.all()).thenReturn(users);

        userService.login("0", "0");

        assertThat(userService.getUser(), is(equalTo(user)));
    }
}
