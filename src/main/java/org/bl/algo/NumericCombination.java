package org.bl.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NumericCombination {
    public static void main(String[] args) {
        // Math.pow(2, n) - 1
        System.out.println(variations(1));
        System.out.println(variations(2));
        System.out.println(variations(3));
        System.out.println(variations(4));
        System.out.println(variations(5));
        System.out.println(variations(6));
        System.out.println(variations(7));
        System.out.println(variations(8));
        System.out.println(variations(9));
        System.out.println(variations(10));
    }

    public static int variations(int n) {
        List<List<Integer>> result = new ArrayList<>();
        IntStream.rangeClosed(1, n).mapToObj(k -> combine(n, k)).forEach(result::addAll);
        System.out.println(result);
        return result.size();
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || n < k) {
            return result;
        }

        List<Integer> item = new ArrayList<>();

        dfs(n, k, 1, item, result); // because it need to begin from 1

        return result;
    }

    private static void dfs(int n, int k, int start, List<Integer> item, List<List<Integer>> res) {
        if (item.size() == k) {
            res.add(new ArrayList<>(item));
            return;
        }

        for (int i = start; i <= n; i++) {
            item.add(i);
            dfs(n, k, i + 1, item, res);
            item.remove(item.size() - 1);
        }
    }
}
