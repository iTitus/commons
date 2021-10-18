package io.github.ititus.math.permutation;

import java.util.*;
import java.util.stream.Collectors;

public final class Permutations {

    private Permutations() {
    }

    public static List<int[]> permute(List<Integer> list) {
        List<int[]> output = new ArrayList<>();
        int[] current = new int[list.size()];
        fillPermutations(output, current, 0, list);
        return output;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<List<T>> permuteObj(List<T> list) {
        List<List<T>> output = new ArrayList<>();
        List<T> current = new ArrayList<>(list.size());
        Collections.addAll(current, (T[]) new Object[list.size()]);
        fillPermutationsObj(output, current, 0, list);
        return output;
    }

    public static <T> Set<Set<T>> permuteWithoutDuplicates(Set<T> set) {
        if (set.isEmpty()) {
            return Set.of(Set.of());
        }
        Set<Set<T>> output =
                new HashSet<>(permuteObj(List.copyOf(set)).stream().map(Set::copyOf).collect(Collectors.toUnmodifiableSet()));
        for (T j : set) {
            output.addAll(permuteWithoutDuplicates(set.stream().filter(n -> !j.equals(n)).collect(Collectors.toUnmodifiableSet())));
        }
        return Set.copyOf(output);
    }

    private static void fillPermutations(List<int[]> output, int[] current, int i, List<Integer> valid) {
        if (valid.isEmpty()) {
            output.add(current);
        } else {
            for (int j : valid) {
                int[] current_ = Arrays.copyOf(current, current.length);
                current_[i] = j;
                fillPermutations(output, current_, i + 1,
                        valid.stream().filter(n -> n != j).collect(Collectors.toList()));
            }
        }
    }

    private static <T> void fillPermutationsObj(List<List<T>> output, List<T> current, int i, List<T> valid) {
        if (valid.isEmpty()) {
            output.add(current);
        } else {
            for (T j : valid) {
                List<T> current_ = new ArrayList<>(current);
                current_.set(i, j);
                fillPermutationsObj(output, current_, i + 1,
                        valid.stream().filter(n -> !j.equals(n)).collect(Collectors.toList()));
            }
        }
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
