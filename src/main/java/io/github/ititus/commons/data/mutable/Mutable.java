package io.github.ititus.commons.data.mutable;

import io.github.ititus.commons.data.DeepToString;
import io.github.ititus.commons.data.ObjectUtil;

import java.util.Objects;

public final class Mutable<T> implements DeepToString {

    private T value;

    private Mutable(T value) {
        this.value = value;
    }

    public static <T> Mutable<T> of(T value) {
        return new Mutable<>(value);
    }

    public static <T> Mutable<T> empty() {
        return new Mutable<>(null);
    }

    public T get() {
        return value;
    }

    public Mutable<T> set(T value) {
        this.value = value;
        return this;
    }

    public boolean isNull() {
        return value == null;
    }

    public boolean isNotNull() {
        return value != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Mutable)) {
            return false;
        }

        Mutable<?> that = (Mutable<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "<" + value + ">";
    }

    @Override
    public String deepToString() {
        return "<" + ObjectUtil.deepToString(value) + ">";
    }
}
