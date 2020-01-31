package com.twu.biblioteca.service;

import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    private User user;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String libraryNumber, String password) {
        User user = getUserByLibraryNumber(libraryNumber);

        if (hasValidPassword(user, password)) {
            this.user = user;

            return true;
        }

        return false;
    }

    private boolean hasValidPassword(User user, String password) {
        return user != null && validateCredentials(user, password);
    }

    private boolean validateCredentials(User user, String password) {
        return user.getPassword().equals(password);
    }

    public User getUserByLibraryNumber(String libraryNumber) {
        return userRepository
                .all()
                .stream()
                .filter(user -> user.getLibraryNumber().equals(libraryNumber))
                .findFirst()
                .orElse(null);
    }

    public User getUser() {
        return user;
    }
}
