package com.company;

import java.io.IOException;

abstract class Menu {

    public static void main(String[] args) throws IOException {
        ButtonStart button = Calculator.getInstance();
        button.start();
    }
}