package io.github.ititus.commons.data;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class IteratorBuffer<T> {

    private final Iterator<? extends T> iterator;
    private final T[] buffer;
    private final int forward, backward;

    private int oldest, cursor, pos;

    @SuppressWarnings("unchecked")
    public IteratorBuffer(Iterator<? extends T> iterator, int forward, int backward) {
        Objects.requireNonNull(iterator);
        if (forward < 0 || backward < 0) {
            throw new IllegalArgumentException("non-negative forward and backward buffer sizes required");
        }

        this.iterator = iterator;
        this.buffer = (T[]) new Object[1 + forward + backward];
        this.forward = forward;
        this.backward = backward;
        this.cursor = backward;
        this.oldest = 0;

        next(forward + 1);

        this.pos = 0;
    }

    public void next() {
        next(1);
    }

    public void next(int steps) {
        for (int i = 0; i < steps; i++) {
            buffer[oldest] = iterator.hasNext() ? Objects.requireNonNull(iterator.next(), "iterator must produce non-null elements") : null;
            oldest++;
            if (oldest >= buffer.length) {
                oldest -= buffer.length;
            }

            cursor++;
            if (cursor >= buffer.length) {
                cursor -= buffer.length;
            }

            pos++;
        }
    }

    public boolean hasNext() {
        return buffer[cursor] != null;
    }

    public T get() {
        return get(0);
    }

    public T getNext() {
        next();
        return get();
    }

    public T get(int offset) {
        if (offset > 0 && offset > forward) {
            throw new IndexOutOfBoundsException("relative index " + offset + " out of bounds for forward buffer " + forward);
        } else if (offset < 0 && -offset > backward) {
            throw new IndexOutOfBoundsException("relative index " + offset + " out of bounds for backward buffer " + backward);
        }

        int i = cursor + offset;
        if (i >= buffer.length) {
            i -= buffer.length;
        } else if (i < 0) {
            i += buffer.length;
        }

        T t = buffer[i];
        if (t == null) {
            throw new NoSuchElementException("could not find element at relative index " + offset);
        }

        return t;
    }

    public int getPos() {
        return pos;
    }
}
