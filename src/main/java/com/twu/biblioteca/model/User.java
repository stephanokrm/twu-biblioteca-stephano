package com.twu.biblioteca.model;

public class User implements Model {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String libraryNumber;

    public User(String libraryNumber, String password, String name, String email, String phone) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Email: %s | Phone Number: %s", name, email, phone);
    }
}
