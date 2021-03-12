package org.bl.leetcode;

import java.util.Arrays;

/**
 * @author beckl
 */
public class CountBits {
    private static int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = Integer.bitCount(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(10)));
        System.out.println(Arrays.toString(countBits(11)));
        System.out.println(Arrays.toString(countBits(12)));
        System.out.println(Arrays.toString(countBits(13)));
    }
}
