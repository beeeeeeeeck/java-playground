package org.bl.sorting;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 选择排序思路
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，
 * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * <p>
 * 时间复杂度，平均是O(power(n, 2)), 最小O(power(n, 2)), 最大O(power(n, 2))
 * 空间复杂度, O(1)
 * 稳定性, 不稳定
 */
public class SelectionSorting {
    public static void main(String[] args) {
        Integer[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println(Arrays.toString(sort(arr)));
    }

    public static <T extends Comparable<T>> T[] sort(T[] target) {
        T[] arr = Arrays.copyOf(target, target.length);
        T temp;
        for (int i = 0; i < arr.length - 1; i++) { // 按(1...n) -> (2...n) -> (3...n) -> (n-1...n)进行分组检查
            int k = i; // 把每一组里第一个没有排序过的元素设置为最小值
            for (int j = k + 1; j < arr.length; j++) { // 循环剩下的元素
                if (arr[j].compareTo(arr[k]) < 0) { // 如果元素 < 现在的最小值, 将此元素设置成为新的最小值
                    k = j; // 记录最小值下标
                }
            }

            if (i != k) { // 如果最小值下标不是第一个元素，则进行交换
                temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }

        return arr;
    }
}
