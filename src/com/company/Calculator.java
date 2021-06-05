package com.company;

import java.io.IOException;

class Calculator extends Expression implements ButtonStart {

    private int firstNum, secondNum, result;
    private static Calculator instance;
    private static int countOfTriggers; // Счётчик триггеров при работе с римскими числами.

    private Calculator() {
    }

    public static Calculator getInstance() { // статический метод getInstance(), который предоставляет объект.
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }

    @Override
    public void start() throws IOException {
        input();
        setFirstNum(getExpElements(0)); // Позиции "0" в массиве expElements соответствует первое число в expression.
        setSecondNum(getExpElements(2)); // Позиции "2" в массиве expElements соответствует второе число в expression.
        getResult();
    }

    private void setFirstNum(String arg) {
        if (arg.matches("[IVXLCDM]+")) {
            arg = romanToArab(arg, 0);
            countOfTriggers++; // Счётчик срабатывает, если элемент expElements соответствует регулярному выражению.
        }

        firstNum = Integer.parseInt(arg);

        checkNum(arg, 0);
    }

    private void setSecondNum(String arg) {
        if (arg.matches("[IVXLCDM]+")) {
            arg = romanToArab(arg, 2);
            countOfTriggers++;
        }

        secondNum = Integer.parseInt(arg);

        checkNum(arg, 2);
    }

    private void getResult() {
        switch (getExpElements(1)) {
            case "+":
                result = firstNum + secondNum;
                break;
            case "-":
                result = firstNum - secondNum;
                break;
            case "*":
                result = firstNum * secondNum;
                break;
            case "/":
                result = firstNum / secondNum;
        }

        checkCountOfTriggers();

        if (countOfTriggers == 2) {
            RomanNumbers.arabToRoman(result);
            checkRomanResult();
        }

        System.out.printf("Результат: %s", result);
    }

    private String romanToArab(String number, int numElement) {
        String[] romanNum = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int numTrigger = 0;
        for (int i = 0; i < romanNum.length; i++) {
            if (number.equals(romanNum[i])) {
                number = String.valueOf(i + 1);
                numTrigger++;
            }
        }

        checkRomanNum(numElement, numTrigger);

        return number;
    }

    // Переменная numElement указывает на номер позиции в expElements.
    // Она введена для более гибкой работы с исключениями относительно первого и второго чисел выражения.
    private void checkNum(String arg, int numElement) {
        String num = (numElement == 0) ? "первого" : "второго";
        try {
            if (isIncorrectValue(Integer.parseInt(arg))) {
                throw new IOException("Неверное значение " + num + " числа.\nПрограмма завершена.");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(0);
        }
    }

    // Метод проверяющий арабские числа на несоответствие указанному диапазону значений.
    private boolean isIncorrectValue(int number) {
        return (1 > number || number > 10);
    }

    // Исключение вызывается при попытке одновременно ввести в состав выражения римское и арабское число.
    // Как правило, это происходит при условии countOfTriggers == 1.
    private void checkRomanNum(int numPos, int numTrigger) {
        int localValue = 0;
        String num = (localValue == numPos) ? "первого" : "второго";
        if (numTrigger == 0) {
            try {
                throw new IOException("Неверное значение " + num + " числа.\nПрограмма завершена.");
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                System.exit(0);
            }
        }
    }

    private void checkCountOfTriggers() {
        try {
            if (countOfTriggers == 1) {
                throw new IOException("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно.\nПрограмма завершена.");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(0);
        }
    }

    private void checkRomanResult() {
        try {
            if (result == 0) {
                throw new IOException("При выполнении функций с римскими цифрами, результат не может быть равен нулю.\nПрограмма завершена.");
            } else if (result < 0) {
                throw new IOException("При выполнении функций с римскими цифрами, результат не может быть отрицательным.\nПрограмма завершена.");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(0);
        }
    }

    private enum RomanNumbers {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100);

        private final int ARAB_NUM;

        RomanNumbers(int arabNum) {
            this.ARAB_NUM = arabNum;
        }

        protected static String arabToRoman(int number) {
            StringBuilder romanNumbers = new StringBuilder();
            RomanNumbers[] numbers = RomanNumbers.values();
            for (int i = numbers.length - 1; i >= 0; i--) {
                while (number >= numbers[i].ARAB_NUM) {
                    romanNumbers.append(numbers[i]);
                    number = number - numbers[i].ARAB_NUM;
                }
            }
            return romanNumbers.toString();
        }
    }
}