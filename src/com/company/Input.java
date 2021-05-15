package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

abstract class Input {

    private String expression; // Ссылка на введённое с консоли setExpression выражение.
    protected String[] expElements; // Ссылка на значения элементов введённого выражения.

    protected void input() throws IOException {
        printInfo();
        setExpression();
        setExpElements(expression); // Разбиваем expression на подстроки и инициализируем значениями этих подстрок массив строк.
    }

    private void setExpression() throws IOException {
        System.out.print("Введите выражение: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            expression = reader.readLine();
            checkExpression();
        reader.close();
    }

    private void setExpElements(String expression){
        expElements = expression.split(" ");

        try {
            if (expElements.length != 3) {
                throw new IOException("Калькулятор работает только с двумя числами.\nПрограмма завершена.");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(0);
        }
    }

    private void printInfo() {
        String info = "Описание функционала калькулятора:"
                + "\n1.Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами;"
                + "\n2.Калькулятор принимает на вход целые числа от 1 до 10 включительно;"
                + "\n3.Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами;"
                + "\n4.Калькулятор умеет работать только с арабскими или римскими цифрами одновременно;"
                + "\n5.Пример написания арифметического выражения: a + b, a - b, a * b, a / b, где а - первое число, b - второе число.\n";
        System.out.println(info);
    }

    private void checkExpression(){
        try {
            if (!expression.matches("[0-9IVXLCDM /*+-]+")) {
                throw new IOException("Неверно введённое выражение.\nПрограмма завершена.");
            } else if (expression.startsWith(" ")) {
                throw new IOException("Пробелы в начале выражения не допускаются.\nПрограмма завершена.");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(0);
        }
    }
}