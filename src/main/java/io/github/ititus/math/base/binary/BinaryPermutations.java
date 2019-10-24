package io.github.ititus.math.base.binary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BinaryPermutations {

    private BinaryPermutations() {
    }

    public static List<BinaryNumber> getPermutations(int nBits) {
        if (nBits <= 0 || nBits >= 31) {
            throw new IllegalArgumentException();
        }

        BinaryNumber[] arr = new BinaryNumber[1 << nBits];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = BinaryNumber.of(i, nBits);
        }

        return List.of(arr);
    }

    public static List<BinaryNumber[]> getAllSubsets(int subsetSize, int nBits) {
        return List.copyOf(choose0(getPermutations(nBits), 0, 0, subsetSize));
    }

    private static List<BinaryNumber[]> choose0(List<BinaryNumber> perms, int start, int pos, int k) {
        if (pos == k) {
            return Collections.singletonList(new BinaryNumber[k]);
        }

        List<BinaryNumber[]> list = new ArrayList<>();
        for (int i = start; i <= perms.size() - (k - pos); i++) {
            List<BinaryNumber[]> rec = choose0(perms, i + 1, pos + 1, k);
            for (BinaryNumber[] bns : rec) {
                bns[pos] = perms.get(i);
            }
            list.addAll(rec);
        }

        return list;
    }
}
