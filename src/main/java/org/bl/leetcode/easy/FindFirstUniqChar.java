package org.bl.leetcode.easy;

/**
 * @author beckl
 */
public class FindFirstUniqChar {
    private static char firstUniqChar(String s) {
        int[] charCounts = new int[26];
        for (char c : s.toCharArray()) {
            charCounts[c - 'a']++;
        }
        for (char c : s.toCharArray()) {
            if (charCounts[c - 'a'] == 1) {
                return c;
            }
        }

        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("abaccdeff"));
    }
}
