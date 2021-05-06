package io.github.ititus.data.mutable;

public final class MutableInt {

    private int value;

    private MutableInt(int value) {
        this.value = value;
    }

    public static MutableInt of(int value) {
        return new MutableInt(value);
    }

    public static MutableInt ofZero() {
        return new MutableInt(0);
    }

    public static MutableInt ofOne() {
        return new MutableInt(1);
    }

    public int get() {
        return value;
    }

    public int getAndIncrement() {
        return value++;
    }

    public int incrementAndGet() {
        return ++value;
    }

    public MutableInt set(int value) {
        this.value = value;
        return this;
    }

    public MutableInt increment() {
        value++;
        return this;
    }

    public MutableInt add(int value) {
        this.value += value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof MutableInt)) {
            return false;
        }

        MutableInt that = (MutableInt) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
