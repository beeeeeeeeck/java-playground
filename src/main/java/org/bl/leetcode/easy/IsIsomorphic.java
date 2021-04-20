package org.bl.leetcode.easy;

/**
 * @author beckl
 */
public class IsIsomorphic {
    private static boolean isIsomorphic(String s, String t) {
        int[] sm = new int[128];
        int[] tm = new int[128];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (sm[sc[i]] != tm[tc[i]]) {
                return false;
            }
            sm[sc[i]] = tm[tc[i]] = i + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));
    }
}
