package com.company;

import java.io.IOException;

import static com.company.RomanNumbers.arabToRoman;

public abstract class Operations extends Input {

    private int firstNum, secondNum, result;
    private static int countOfTriggers;

    protected void operations() throws IOException {
        input();
        setFirstNum(firstValue, FIRST_NUM_POS);
        setSecondNum(secondValue, SEC_NUM_POS);
        getResult();
    }

    private void setFirstNum(String arg, int numPos) {
        if (arg.matches("[IVXLCDM]+")){
            arg = romanToArab(arg, numPos);
            countOfTriggers++;
        }

        firstNum = Integer.parseInt(arg);

        checkNum(arg, numPos);
    }

    private void setSecondNum(String arg, int numPos) {
        if (arg.matches("[IVXLCDM]+")){
            arg = romanToArab(arg, numPos);
            countOfTriggers++;
        }

        secondNum = Integer.parseInt(arg);

        checkNum(arg, numPos);
    }

    private void getResult(){
        switch (function){
            case "+": result = firstNum + secondNum; break;
            case "-": result = firstNum - secondNum; break;
            case "*": result = firstNum * secondNum; break;
            case "/": result = firstNum / secondNum;
        }

        checkCountOfTriggers();

        if (countOfTriggers == 2) {
            checkRomanResult();
            System.out.println("Результат: " + arabToRoman(result));
        } else {
            System.out.println("Результат: " + result);
        }
    }

    private String romanToArab(String number, int numPos) {
        String[] romanNum = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int numTrigger = 0;
        for (int i = 0; i < romanNum.length; i++) {
            if (number.equals(romanNum[i])) {
                number = String.valueOf(i + 1);
                numTrigger++;
            }
        }

        checkRomanNum(numPos, numTrigger);

        return number;
    }

    private void checkNum(String arg, int numPos){
        String num = (numPos == 0) ? "первого" : "второго";
        try {
            if(isIncorrectValue(Integer.parseInt(arg))){
                throw new IOException("Неверное значение " +  num + " числа.\nПрограмма завершена.");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(0);
        }
    }

    private boolean isIncorrectValue(int number) {
        return (1 > number || number > 10);
    }

    private void checkRomanNum(int numPos, int numTrigger) {
        int localValue = 0;
        String num = (localValue == numPos) ? "первого" : "второго";
        if (numTrigger == 0) {
            try {
                throw new IOException("Неверное значение " +  num + " числа.\nПрограмма завершена.");
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                System.exit(0);
            }
        }
    }

    private void checkCountOfTriggers(){
        try {
            if (countOfTriggers == 1){
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
}


