package io.github.ititus.commons.data.pair;

import io.github.ititus.commons.data.ObjectUtil;

import java.util.Objects;

final class IntObjPairImpl<B> implements IntObjPair<B> {

    private final int a;
    private final B b;

    IntObjPairImpl(int a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int aInt() {
        return a;
    }

    @Override
    public B b() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof IntObjPair)) {
            return false;
        }

        IntObjPair<?> pair = (IntObjPair<?>) o;
        return a == pair.a() && Objects.equals(b, pair.b());
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "<" + a + "," + b + ">";
    }

    @Override
    public String deepToString() {
        return "<" + a + "," + ObjectUtil.deepToString(b) + ">";
    }
}
