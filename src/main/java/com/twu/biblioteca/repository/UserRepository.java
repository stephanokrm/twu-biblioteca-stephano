package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRepository implements Repository<User> {
    private static final List<User> users = new ArrayList<>(Arrays.asList(
            new User("xxx-xxxx", "Password", "Name", "email@gmail.com", "(00) 00000-0000")
    ));

    @Override
    public List<User> all() {
        return users;
    }
}
