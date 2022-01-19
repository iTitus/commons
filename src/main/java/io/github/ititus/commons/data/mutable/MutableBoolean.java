package io.github.ititus.commons.data.mutable;

import java.util.function.Supplier;

public final class MutableBoolean {

    private boolean value;

    private MutableBoolean(boolean value) {
        this.value = value;
    }

    public static MutableBoolean of(boolean value) {
        return new MutableBoolean(value);
    }

    public static MutableBoolean ofTrue() {
        return new MutableBoolean(true);
    }

    public static MutableBoolean ofFalse() {
        return new MutableBoolean(false);
    }

    public boolean get() {
        return value;
    }

    public MutableBoolean set(boolean value) {
        this.value = value;
        return this;
    }

    public MutableBoolean toggle() {
        this.value = !this.value;
        return this;
    }

    public MutableBoolean runIfTrue(Runnable r) {
        if (value) {
            r.run();
        }

        return this;
    }

    public MutableBoolean runIfFalse(Runnable r) {
        if (!value) {
            r.run();
        }

        return this;
    }

    public MutableBoolean run(Runnable if_, Runnable else_) {
        if (value) {
            if_.run();
        } else {
            else_.run();
        }

        return this;
    }

    public <T> T get(Supplier<? extends T> if_, Supplier<? extends T> else_) {
        if (value) {
            return if_.get();
        }

        return else_.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof MutableBoolean)) {
            return false;
        }

        MutableBoolean that = (MutableBoolean) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(value);
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
