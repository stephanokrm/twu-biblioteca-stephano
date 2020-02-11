package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Checkout;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.model.Work;
import com.twu.biblioteca.repository.CheckoutRepository;

import java.util.List;

public abstract class CheckoutService {
    private final CheckoutRepository checkoutRepository;

    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    protected Checkout checkout(Work work, User user) {
        Checkout checkout = new Checkout(work, user);

        checkoutRepository.add(checkout);

        return checkout;
    }

    protected boolean workIsAvailable(Work work) {
        return !workIsNotAvailable(work);
    }

    protected boolean workIsNotAvailable(Work work) {
        return checkoutRepository
                .all()
                .stream()
                .anyMatch(checkout -> checkout.getWork().getTitle().equals(work.getTitle()));
    }

    protected void returnWork(Work work) {
        checkoutRepository
                .all()
                .removeIf(checkout -> checkout.getWork().getTitle().equals(work.getTitle()));
    }

    protected List<Checkout> getAllCheckedOutWorks() {
        return checkoutRepository.all();
    }
}
