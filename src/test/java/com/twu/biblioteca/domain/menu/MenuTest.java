package com.twu.biblioteca.domain.menu;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.domain.menu.option.ExitOption;
import com.twu.biblioteca.domain.menu.option.ListOfBooksOption;
import com.twu.biblioteca.exception.InvalidMenuOptionException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

public class MenuTest extends TestCase {
    private Menu menu;

    @Override
    public void setUp() {
        super.setUp();

        menu = new Menu(out);
    }

    @Test
    public void boot() {
        menu.boot();

        assertThat(menu.isBooted(), is(true));
    }

    @Test
    public void hasListOfBookListOptionAfterBoot() {
        menu.boot();

        boolean hasListOfBooksOption = menu.hasOption(ListOfBooksOption.NUMBER);

        assertThat(hasListOfBooksOption, is(true));
    }

    @Test
    public void hasExitAfterOptionBoot() {
        menu.boot();

        boolean hasExitOption = menu.hasOption(ExitOption.NUMBER);

        assertThat(hasExitOption, is(true));
    }

    @Test
    public void openMenu() {
        menu.open();

        verify(out).println("- Menu\n");
    }

    @Test(expected = InvalidMenuOptionException.class)
    public void notifyWhenChooseAnInvalidOption() throws InvalidMenuOptionException {
        menu.run(-1);
    }
}
