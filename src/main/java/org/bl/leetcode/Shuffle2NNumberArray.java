package org.bl.leetcode;

import java.util.Arrays;

/**
 * @author beckl
 */
public class Shuffle2NNumberArray {
    public static int[] shuffle(int[] nums, int n) {
        int len = 2 * n;
        // List<Integer> ans = new ArrayList<>(len);
        // for (int i = 0; i < n; i++) {
        //     ans.add(nums[i]);
        //     ans.add(nums[i + n]);
        // }
        //
        // return ans.stream().mapToInt(Integer::intValue).toArray();
        int[] ret = new int[len];
        for (int i = 0; i < n; i++) {
            ret[i * 2] = nums[i];
            ret[i * 2 + 1] = nums[n + i];
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3)));
        System.out.println(Arrays.toString(shuffle(new int[]{1, 2, 3, 4, 4, 3, 2, 1}, 4)));
        System.out.println(Arrays.toString(shuffle(new int[]{1, 1, 2, 2}, 2)));
    }
}
