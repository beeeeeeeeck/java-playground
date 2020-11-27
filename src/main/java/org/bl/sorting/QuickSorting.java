package org.bl.sorting;

import java.util.Arrays;

/**
 * 快速排序算法步骤:
 * 1. 从数列中挑出一个元素，称为 “基准”（pivot）;
 * 2. 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 *    在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 3. 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
 *
 * 时间复杂度，平均是O(n log n), 最小O(n log n), 最大O(power(n, 2))
 * 空间复杂度, O(log n)
 * 稳定性, 不稳定
 */
public class QuickSorting {

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 1, 5};
        System.out.println(Arrays.toString(sort(arr)));
    }

    private static int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        return quickSort(arr, 0, arr.length - 1);
    }

    /**
     * @param arr source array
     * @param left inclusive
     * @param right inclusive
     * @return sorted array
     */
    private static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）= arr[left]
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[left]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, left, index - 1);
        return index - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
