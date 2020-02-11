package com.twu.biblioteca.model;

public class Checkout implements Model {
    private final Work work;
    private final User user;

    public Checkout(Work work, User user) {
        this.work = work;
        this.user = user;
    }

    public Work getWork() {
        return work;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return String.format("Work: %s | Renter Library Number: %s", work.getTitle(), user.getLibraryNumber());
    }
}
