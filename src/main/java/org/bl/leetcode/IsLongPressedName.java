package org.bl.leetcode;

/**
 * @author beckl
 */
public class IsLongPressedName {
    public static boolean isLongPressedName(String name, String typed) {
        // if (name == null || typed == null) return false;
        // if (name.length() > typed.length()) return false;
        //
        // int i = 0, j = 0;
        // while (i < name.length() && j < typed.length()) {
        //     if (name.charAt(i) != typed.charAt(j)) return false;
        //     if (i + 1 < name.length() && name.charAt(i) == name.charAt(i + 1)) {
        //         i++;
        //         j++;
        //     } else if (j + 1 < typed.length() && name.charAt(i) == typed.charAt(j + 1)) {
        //         j++;
        //     } else {
        //         i++;
        //         j++;
        //     }
        // }
        //
        // if (i < name.length()) return false;
        // if (j < typed.length()) return false;
        //
        // return true;
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }

    public static void main(String[] args) {
        System.out.println(isLongPressedName("alex", "aaleex"));
        System.out.println(isLongPressedName("alex", "aaleexa"));
        System.out.println(isLongPressedName("saeed", "ssaaedd"));
        System.out.println(isLongPressedName("leelee", "lleeelee"));
        System.out.println(isLongPressedName("laiden", "laiden"));
    }
}
