package org.bl.leetcode;

/**
 * @author beckl
 */
public class ContainsNearbyDuplicateNum {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            while (j <= i + k && j < nums.length) {
                if (nums[i] == nums[j]) {
                    return true;
                }
                j++;
            }
        }

        // for (int i = 0; i < nums.length; ++i) {
        //     for (int j = Math.max(i - k, 0); j < i; ++j) {
        //         if (nums[i] == nums[j]) return true;
        //     }
        // }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }
}