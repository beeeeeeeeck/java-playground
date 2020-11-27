package org.bl.sorting;

import java.util.Arrays;

/**
 * 插入排序是一种最简单的排序方法，它的基本思想是将一个记录插入到已经排好序的有序表中，从而一个新的、记录数增 1 的有序表
 * 在其实现过程使用双层循环，外层循环对除了第一个元素之外的所有元素，内层循环对当前元素前面有序表进行待插入位置查找，并进行移动
 * <p>
 * 时间复杂度，平均是O(power(n, 2)), 最小O(n), 最大O(power(n, 2))
 * 空间复杂度, O(1)
 * 稳定性, 稳定
 */
public class InsertionSorting {
    public static void main(String[] args) {
        // Integer[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        Integer[] arr = {6, 3, 4, 8, 5, 7};
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(sort(arr)));
    }

    public static <T extends Comparable<T>> T[] sort(T[] target) {
        T[] arr = Arrays.copyOf(target, target.length);
        T node; // 待插入的元素
        int j;
        for (int i = 1; i < arr.length; i++) { // 假定第1个元素为已经排好顺序的，逐个检查(2...n)之间的元素
            node = arr[i]; // 假定未排序好的第1个元素为待插入的元素
            j = i - 1; // 找到已排序好的最后一个元素下标, 第一次循环以第一个元素来有序表的最后一个
            while (j >= 0 && node.compareTo(arr[j]) < 0) { // 比较待插入的元素与已排序好的每个元素，如果排序过的元素 > 待插入的元素，则右移排序过的元素一格
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = node; // 直到待插入的元素不小于第j个排序过的元素, 将其插入到数组中
        }

        return arr;
    }
}
