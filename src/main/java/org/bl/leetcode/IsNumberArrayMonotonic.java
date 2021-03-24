package org.bl.leetcode;

/**
 * @author beckl
 */
public class IsNumberArrayMonotonic {
    public static boolean isMonotonic(int[] A) {
        int len = A.length;
        if (len == 1) return true;
        int i = 0, j = len - 1;
        return isMonotonic(A, i, j, A[i] < A[j]);
    }

    private static boolean isMonotonic(int[] nums, int start, int end, boolean increasing) {
        if (start == end) return true;

        if (increasing) {
            if (nums[start] > nums[end]) {
                return false;
            }
        } else {
            if (nums[start] < nums[end]) {
                return false;
            }
        }

        int mid = (start + end) / 2;
        if (mid == start) return true;
        return isMonotonic(nums, start, mid, increasing) && isMonotonic(nums, mid, end, increasing);
    }

    public static void main(String[] args) {
        System.out.println(isMonotonic(new int[]{1, 2, 2, 3}));
        System.out.println(isMonotonic(new int[]{6, 5, 4, 4}));
        System.out.println(isMonotonic(new int[]{1, 3, 2}));
        System.out.println(isMonotonic(new int[]{1, 2, 4, 5}));
        System.out.println(isMonotonic(new int[]{1, 1, 1, 1}));
    }
}
