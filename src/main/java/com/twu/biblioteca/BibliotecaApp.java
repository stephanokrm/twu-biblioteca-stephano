package com.twu.biblioteca;

import com.twu.biblioteca.menu.Menu;

public class BibliotecaApp {
    public static void main(String[] args) {
        Welcome welcome = new Welcome(System.out);
        welcome.show();

        Menu menu = new Menu(System.out);
        menu.show();
    }
}
