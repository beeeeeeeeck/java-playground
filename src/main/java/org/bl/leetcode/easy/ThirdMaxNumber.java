package org.bl.leetcode.easy;

import java.util.Arrays;

/**
 * @author beckl
 */
public class ThirdMaxNumber {
    public static int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        if (len < 3) return nums[len - 1];
        int count = 1;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            count++;
            if (count == 3) {
                return nums[i];
            }
        }

        return nums[len - 1];
    }

    public static void main(String[] args) {
        System.out.println(thirdMax(new int[]{3, 2, 1}));
        System.out.println(thirdMax(new int[]{3, 2, 2, 1}));
        System.out.println(thirdMax(new int[]{2, 1}));
    }
}
