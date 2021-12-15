package io.github.ititus.data.pair.impl;

import io.github.ititus.data.ArrayUtil;
import io.github.ititus.data.pair.IntIntPair;

public final class IntIntPairImpl implements IntIntPair {

    private final int a;
    private final int b;

    public IntIntPairImpl(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int aInt() {
        return a;
    }

    @Override
    public int bInt() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof IntIntPair)) {
            return false;
        }

        IntIntPair that = (IntIntPair) o;
        return a == that.a() && b == that.b();
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
