package com.twu.biblioteca.service;

import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;

    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void getUserByLibraryNumber() {
        User user = new User("0", "0");

        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.all()).thenReturn(users);

        assertThat(userService.getUserByLibraryNumber("0"), is(equalTo(user)));
    }
}
