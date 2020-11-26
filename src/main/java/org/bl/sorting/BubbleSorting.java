package org.bl.sorting;

import java.util.Arrays;

/**
 * 冒泡排序算法的原理如下:
 * 1. 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 2. 对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
 * 3. 针对所有的元素重复以上的步骤，除了最后一个。
 * 4. 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 *
 * 时间复杂度，平均是O(power(n, 2)), 最小O(n), 最大O(power(n, 2))
 * 空间复杂度, O(1)
 * 稳定性, 稳定
 */
public class BubbleSorting {

    public static void main(String[] args) {
        Integer[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        T temp;
        boolean swapped = true;
        for (int i = 0; i < arr.length - 1 && swapped; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
        }
        // while(swapped) { // 有交换则继续处理
        //     swapped = false; // 假定不会交换
        //     for (int i = 0; i < arr.length - 1; i++) { // 按(1...n) -> (1...n-1) -> (1...n-2) -> (1...2)进行分组检查
        //         for (int j = 0; j < arr.length - 1 - i; j++) { // 检查每一组里相邻的左边元素和右边元素
        //             if (arr[j].compareTo(arr[j + 1]) > 0) { // 如果左边元素 > 右边元素就进行交换
        //                 temp = arr[j];
        //                 arr[j] = arr[j + 1];
        //                 arr[j + 1] = temp;
        //                 swapped = true; // 设置交换旗帜变量确保进行下一组检查
        //             }
        //         }
        //     }
        //     if (!swapped) { // 没有发生交换则判定当前组及后面的每一组都已经是正序，无需再进行处理
        //         break;
        //     }
        // }
    }
}