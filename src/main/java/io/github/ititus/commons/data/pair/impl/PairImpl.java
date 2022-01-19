package io.github.ititus.commons.data.pair.impl;

import io.github.ititus.commons.data.ObjectUtil;
import io.github.ititus.commons.data.pair.Pair;

import java.util.Objects;

public final class PairImpl<A, B> implements Pair<A, B> {

    private final A a;
    private final B b;

    public PairImpl(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public A a() {
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
        } else if (!(o instanceof Pair)) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(a, pair.a()) && Objects.equals(b, pair.b());
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
        return "<" + ObjectUtil.deepToString(a) + "," + ObjectUtil.deepToString(b) + ">";
    }
}
