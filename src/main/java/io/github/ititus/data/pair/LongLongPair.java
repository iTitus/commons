package io.github.ititus.data.pair;

import io.github.ititus.data.ArrayUtil;

public final class LongLongPair {

    private final long a;
    private final long b;

    private LongLongPair(long a, long b) {
        this.a = a;
        this.b = b;
    }

    public long a() {
        return a;
    }

    public long b() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LongLongPair)) {
            return false;
        }
        LongLongPair that = (LongLongPair) o;
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

    public static LongLongPair of(long a, long b) {
        return new LongLongPair(a, b);
    }
}
