package io.github.ititus.commons.data.pair.impl;

import io.github.ititus.commons.data.ArrayUtil;
import io.github.ititus.commons.data.pair.LongLongPair;

public final class LongLongPairImpl implements LongLongPair {

    private final long a;
    private final long b;

    public LongLongPairImpl(long a, long b) {
        this.a = a;
        this.b = b;
    }

    public long aLong() {
        return a;
    }

    @Override
    public Long a() {
        return a;
    }

    public long bLong() {
        return b;
    }

    @Override
    public Long b() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof LongLongPair)) {
            return false;
        }

        LongLongPair that = (LongLongPair) o;
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
