package org.bl.leetcode.easy;

import java.util.Arrays;

/**
 * @author beckl
 */
public class TransposeMatrix {
    public static int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] transposed = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    public static void main(String[] args) {
        // [[1,4,7],[2,5,8],[3,6,9]]
        System.out.println(Arrays.toString(transpose(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
        System.out.println(Arrays.toString(transpose(new int[][]{{1, 2, 3}, {4, 5, 6}})));
    }
}
