package org.bl.leetcode.medium;

public class IntegerToRomanNumber {
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < values.length && num >= 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        IntegerToRomanNumber convertor = new IntegerToRomanNumber();
        System.out.println(convertor.intToRoman(3));
        System.out.println(convertor.intToRoman(4));
        System.out.println(convertor.intToRoman(9));
        System.out.println(convertor.intToRoman(11));
        System.out.println(convertor.intToRoman(58));
        System.out.println(convertor.intToRoman(1_900));
        System.out.println(convertor.intToRoman(1_994));
        System.out.println(convertor.intToRoman(2_994));
    }
}
