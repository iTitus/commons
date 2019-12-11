package io.github.ititus.data;

public final class Bag<T> {

    private T content;

    public Bag() {
        this(null);
    }

    public Bag(T content) {
        this.content = content;
    }

    public T get() {
        return content;
    }

    public void set(T content) {
        this.content = content;
    }
}
