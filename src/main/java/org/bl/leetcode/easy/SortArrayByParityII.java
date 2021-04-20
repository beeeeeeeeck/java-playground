package org.bl.leetcode.easy;

import java.util.Arrays;

/**
 * @author beckl
 */
public class SortArrayByParityII {
    private static int[] sortArrayByParityII(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];

        int i = 0;
        for (int x : nums) {
            if (x % 2 == 0) {
                ans[i] = x;
                i += 2;
            }
        }
        i = 1;
        for (int x : nums) {
            if (x % 2 == 1) {
                ans[i] = x;
                i += 2;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArrayByParityII(new int[]{648, 831, 560, 986, 192, 424, 997, 829, 897, 843})));
        // [648, 831, 560, 997, 192, 897, 986, 829, 424, 843]
    }
}
