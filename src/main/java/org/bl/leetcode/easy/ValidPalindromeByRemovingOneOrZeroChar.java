package org.bl.leetcode.easy;

/**
 * @author beckl
 */
public class ValidPalindromeByRemovingOneOrZeroChar {
    // public static boolean validPalindrome(String s) {
    //     if (isValidPalindrome(s, -1)) return true;
    //     for (int i = 0; i < s.length(); i++) {
    //         if (isValidPalindrome(s, i)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }
    //
    // private static boolean isValidPalindrome(String s, int excluded) {
    //     Deque<Character> queue = new LinkedList<>();
    //     for (int i = 0; i < s.length(); i++) {
    //         if (i == excluded) continue;
    //         queue.add(s.charAt(i));
    //     }
    //     while (!queue.isEmpty()) {
    //         Character left = queue.pollFirst();
    //         Character right = queue.pollLast();
    //         if (left != null && right != null && !left.equals(right)) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    public static boolean validPalindrome(String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            char c1 = s.charAt(low), c2 = s.charAt(high);
            if (c1 == c2) {
                ++low;
                --high;
            } else {
                return validPalindrome(s, low, high - 1) || validPalindrome(s, low + 1, high);
            }
        }
        return true;
    }

    public static boolean validPalindrome(String s, int low, int high) {
        for (int i = low, j = high; i < j; ++i, --j) {
            char c1 = s.charAt(i), c2 = s.charAt(j);
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(validPalindrome("aba"));
        System.out.println(validPalindrome("abba"));
        System.out.println(validPalindrome("abbac"));
    }
}
