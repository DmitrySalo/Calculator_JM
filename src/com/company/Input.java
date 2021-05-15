package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Input {

    private String expression;
    protected static String function;
    protected static String firstValue;
    protected static String secondValue;
    protected static final int FUNCTION_POS = 1;
    protected static final int FIRST_NUM_POS = 0;
    protected static final int SEC_NUM_POS = 2;

    protected void input() throws IOException {
        printInfo();
        setExpression();
        setValues(expression);
    }

    private void setExpression() throws IOException {
        System.out.print("Введите выражение: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            expression = reader.readLine();
            checkExpression();
        reader.close();
    }

    private void setValues(String expression){
        String[] expElements = expression.split(" ");
        firstValue = expElements[FIRST_NUM_POS];
        secondValue = expElements[SEC_NUM_POS];
        function = expElements[FUNCTION_POS];

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
            if (!expression.matches("[0-9IVXCLM /*+-]+")) {
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

