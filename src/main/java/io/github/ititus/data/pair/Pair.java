package io.github.ititus.data.pair;

import java.util.Objects;

public final class Pair<A, B> {

    private final A a;
    private final B b;

    private Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    public static IntIntPair of(int a, int b) {
        return IntIntPair.of(a, b);
    }

    public static LongLongPair of(long a, long b) {
        return LongLongPair.of(a, b);
    }

    public A a() {
        return a;
    }

    public B b() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(a, pair.a) && Objects.equals(b, pair.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "<" + a + "," + b + ">";
    }
}