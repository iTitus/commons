package io.github.ititus.data;

public final class Bag<T> implements DeepToString {

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

    @Override
    public String toString() {
        return "<" + content + ">";
    }

    @Override
    public String deepToString() {
        return "<" + ObjectUtil.deepToString(content) + ">";
    }
}
