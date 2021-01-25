package io.github.ititus.data.pair;

import io.github.ititus.data.ArrayUtil;

public final class IntIntPair {

    private final int a;
    private final int b;

    private IntIntPair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public static IntIntPair of(int a, int b) {
        return new IntIntPair(a, b);
    }

    public int a() {
        return a;
    }

    public int b() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IntIntPair)) {
            return false;
        }
        IntIntPair that = (IntIntPair) o;
        return a == that.a && b == that.b;
    }

    @Override
    public int hashCode() {
        return ArrayUtil.hash(a, b);
    }

    @Override
    public String toString() {
        return "<" + a + "," + b + ">";
    }
}
