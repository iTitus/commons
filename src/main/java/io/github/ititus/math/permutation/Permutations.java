package io.github.ititus.math.permutation;

import java.util.*;

public final class Permutations {

    private Permutations() {
    }

    public static <T> List<List<T>> permute(Collection<T> src, int n) {
        // System.out.println("permute: n=" + n);
        if (n < 0) {
            throw new IllegalArgumentException("n=" + n);
        }

        List<List<T>> l = new ArrayList<>();
        for (int[] arr : getAllArraysWithSum(src.size(), n)) {
            List<T> perm = new ArrayList<>(n);
            Iterator<T> it = src.iterator();

            for (int count : arr) {
                T t = it.next();

                for (int i = 0; i < count; i++) {
                    perm.add(t);
                }
            }

            l.add(perm);
        }

        if (n > 0) {
            l.addAll(permute(src, n - 1));
        }
        return l;
    }

    public static List<int[]> getAllArraysWithSum(int length, int sum) {
        // System.out.println("getAllArraysWithSum: length=" + length + " sum=" + sum);
        if (sum < 0) {
            throw new IllegalArgumentException("length=" + length + " sum=" + sum);
        } else if (length == 0 && sum > 0) {
            return Collections.emptyList();
        } else if (sum == 0) {
            return Collections.singletonList(new int[length]);
        }

        List<int[]> l = new ArrayList<>();

        for (int s = 0; s <= sum; s++) {
            for (int[] sub : getAllArraysWithSum(length - 1, sum - s)) {
                int[] a = new int[length];
                a[0] = s;
                System.arraycopy(sub, 0, a, 1, sub.length);
                l.add(a);
            }
        }

        return l;
    }
}
