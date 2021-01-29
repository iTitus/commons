package io.github.ititus.data;

public final class Bag<T> implements DeepToString {

    private T content;

    private Bag(T content) {
        this.content = content;
    }

    public static <T> Bag<T> of(T content) {
        return new Bag<>(content);
    }

    public static <T> Bag<T> empty() {
        return new Bag<>(null);
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
