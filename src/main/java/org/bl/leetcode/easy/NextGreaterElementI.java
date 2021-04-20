package org.bl.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 典型单调栈解法
 * @author beckl
 */
public class NextGreaterElementI {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Map<Integer, Integer> map = new HashMap<>();
        // for (int i = 0; i < nums2.length; i++) {
        //     int j = i + 1;
        //     int res = -1;
        //     while (j < nums2.length) {
        //         if (nums2[i] < nums2[j]) {
        //             res = nums2[j];
        //             break;
        //         }
        //         j++;
        //     }
        //     map.put(nums2[i], res);
        // }
        // int[] ans = new int[nums1.length];
        // for (int i = 0; i < nums1.length; i++) {
        //     ans[i] = map.get(nums1[i]);
        // }
        //
        // return ans;

        Stack<Integer> stk = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (!stk.empty() && num > stk.peek()) {
                map.put(stk.pop(), num);
            }
            stk.push(num);
        }
        while (!stk.empty()) {
            map.put(stk.pop(), -1);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
        int[] nums3 = new int[]{1, 3, 5, 2, 4};
        int[] nums4 = new int[]{6, 5, 4, 3, 2, 1, 7};
        System.out.println(Arrays.toString(nextGreaterElement(nums3, nums4)));
    }
}
