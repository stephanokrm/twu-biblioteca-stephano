package com.twu.biblioteca.service;

import com.twu.biblioteca.exception.AuthenticationException;
import com.twu.biblioteca.model.User;

public class AuthService {
    private User user;
    private UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    public boolean isAuthenticated() {
        return user != null;
    }

    public boolean isNotAuthenticated() {
        return !isAuthenticated();
    }

    public void authenticate(String libraryNumber, String password) throws AuthenticationException {
        User user = userService.getUserByLibraryNumber(libraryNumber);

        if (!hasValidUser(user) || !hasValidPassword(user, password)) {
            throw new AuthenticationException("These credentials do not match our records.");
        }

        setUser(user);
    }

    private boolean hasValidPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    private boolean hasValidUser(User user) {
        return user != null;
    }

    public void actingAs(User user) {
        setUser(user);
    }
}
