package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.domain.menu.Menu;
import org.junit.Test;

import static org.mockito.Mockito.verify;

public class CheckoutABookOptionTest extends TestCase {
    private Menu menu;
    private CheckoutABookOption checkoutABookOption;

    @Override
    public void setUp() {
        super.setUp();

        checkoutABookOption = new CheckoutABookOption(out);
        menu = new Menu(out);
        menu.addOption(checkoutABookOption);
    }

    @Test
    public void showCheckoutABookOption() {
        menu.open();

        verify(out).println("2. Checkout a Book");
    }
}
