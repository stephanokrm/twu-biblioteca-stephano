package com.twu.biblioteca;

import com.twu.biblioteca.menu.Menu;
import com.twu.biblioteca.menu.option.ListOfBooksOption;
import com.twu.biblioteca.menu.option.MenuOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    public static void main(String[] args) {
        Welcome welcome = new Welcome(System.out);
        welcome.show();

        List<MenuOption> menuOptions = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();
        menuOptions.add(new ListOfBooksOption(System.out, books));

        Menu menu = new Menu(System.out, menuOptions);
        menu.open();

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        menu.run(option);
    }
}
