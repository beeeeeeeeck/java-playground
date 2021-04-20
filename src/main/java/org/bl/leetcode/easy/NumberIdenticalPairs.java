package org.bl.leetcode.easy;

import java.util.Arrays;

/**
 * @author beckl
 */
public class NumberIdenticalPairs {
    public static int numIdenticalPairs(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        int sum = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                count += sum;
                sum++;
            } else {
                sum = 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numIdenticalPairs(new int[]{1, 2, 3, 1, 1, 3}));
        System.out.println(numIdenticalPairs(new int[]{1, 1, 1, 1}));
        System.out.println(numIdenticalPairs(new int[]{1, 2, 3}));
    }
}
