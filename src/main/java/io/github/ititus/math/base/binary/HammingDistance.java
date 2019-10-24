package io.github.ititus.math.base.binary;

public final class HammingDistance {

    private HammingDistance() {
    }

    public static int hammingDistance(BinaryNumber a, BinaryNumber b) {
        if (a.getLength() != b.getLength()) {
            throw new IllegalArgumentException();
        }
        int n = 0;
        for (int i = 0; i < a.getLength(); i++) {
            if (a.get(i) != b.get(i)) {
                n++;
            }
        }
        return n;
    }

    public static int hammingDistance(BinaryNumber... arr) {
        int min = -1;
        for (BinaryNumber a : arr) {
            for (BinaryNumber b : arr) {
                if (a != b) {
                    int n = hammingDistance(a, b);
                    if (min == -1 || n < min) {
                        min = n;
                    }
                }
            }
        }
        return min;
    }

    public static int hammingDistance(Iterable<BinaryNumber> iterable) {
        int min = -1;
        for (BinaryNumber a : iterable) {
            for (BinaryNumber b : iterable) {
                if (a != b) {
                    int n = hammingDistance(a, b);
                    if (min == -1 || n < min) {
                        min = n;
                    }
                }
            }
        }
        return min;
    }
}
