package com.company;

public enum RomanNumbers {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100);

    private final int ARAB_NUM;

    RomanNumbers(int arabNum) {
        this.ARAB_NUM = arabNum;
    }
    static String arabToRoman(int number){
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
