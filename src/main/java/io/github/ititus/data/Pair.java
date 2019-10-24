package io.github.ititus.data;

import java.util.Map;
import java.util.Objects;

public final class Pair<A, B> implements Printable {

    private final A a;
    private final B b;

    private Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    public A getA() {
        return a;
    }

    public B getB() {
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
    public String getPrefix() {
        return "Pair";
    }

    @Override
    public void getPrintableFields(Map<String, String> fields) {
        fields.put("a", Objects.toString(a));
        fields.put("b", Objects.toString(b));
    }

    @Override
    public String toString() {
        return Printable.toPrintableString(this);
    }
}