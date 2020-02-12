package com.twu.biblioteca.service;

import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByLibraryNumber(String libraryNumber) {
        return userRepository
                .all()
                .stream()
                .filter(user -> user.getLibraryNumber().equalsIgnoreCase(libraryNumber))
                .findFirst()
                .orElse(null);
    }
}
