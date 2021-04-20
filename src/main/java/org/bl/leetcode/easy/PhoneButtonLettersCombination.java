package org.bl.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author beckl
 */
public class PhoneButtonLettersCombination {
    // public static List<String> letterCombinations(String digits) {
    //     if (digits == null || digits.length() == 0) return List.of();
    //     List<List<String>> options = new ArrayList<>();
    //     for (char c : digits.toCharArray()) {
    //         options.add(getPhoneLetters(c - '0'));
    //     }
    //
    //     return options.stream().reduce(new ArrayList<>(), (memo, letters) -> {
    //         if (memo.isEmpty()) return letters;
    //         List<String> temp = new ArrayList<>();
    //         for (String tempStr : memo) {
    //             for (String c : letters) {
    //                 temp.add(tempStr + c);
    //             }
    //         }
    //         return temp;
    //     });
    // }
    //
    // private static List<String> getPhoneLetters(Integer btnNumber) {
    //     if (btnNumber == null || btnNumber <= 1 || btnNumber > 9) return List.of();
    //     int num = (btnNumber == 7 || btnNumber == 9) ? 4 : 3;
    //     List<String> letters = new ArrayList<>();
    //     for (int i = 0; i < num; i++) {
    //         int offset = i + (btnNumber - 2) * 3 + (btnNumber >= 8 ? 1 : 0);
    //         letters.add(String.valueOf((char) ('a' + offset)));
    //     }
    //     return letters;
    // }

    public static List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    public static void main(String[] args) {
        // IntStream.rangeClosed(2, 9).forEach(i -> System.out.println(getPhoneLetters(i)));
        System.out.println(letterCombinations("23"));
    }
}
