package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Checkout;

import java.util.ArrayList;
import java.util.List;

public class CheckoutRepository implements Repository<Checkout> {
    private static final List<Checkout> checkouts = new ArrayList<>();

    public void add(Checkout checkout) {
        checkouts.add(checkout);
    }

    public List<Checkout> all() {
        return checkouts;
    }
}
