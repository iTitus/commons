package io.github.ititus.commons.data.array;

import java.util.Arrays;

public final class GrowableCharArray implements Appendable {

    private static final int INITIAL_CAPACITY = 16;
    private static final char[] EMPTY = {};

    private int size;
    private char[] array;

    public GrowableCharArray() {
        this.size = 0;
        this.array = EMPTY;
    }

    public GrowableCharArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }

        this.size = 0;
        this.array = new char[initialCapacity];
    }

    public char[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public GrowableCharArray append(CharSequence csq) {
        return append(csq, 0, csq.length());
    }

    @Override
    public GrowableCharArray append(CharSequence csq, int start, int end) {
        if (csq == null) {
            csq = "null";
        }

        if (start < 0 || end < 0 || start > end || end > csq.length()) {
            throw new IndexOutOfBoundsException();
        }

        int length = end - start + 1;
        growIfNecessary(size + length);
        if (csq instanceof String str) {
            str.getChars(start, end, array, size);
            size += length;
        } else {
            for (int i = start; i < end; i++) {
                array[size++] = csq.charAt(i);
            }
        }

        return this;
    }

    @Override
    public GrowableCharArray append(char c) {
        growIfNecessary(size + 1);
        array[size++] = c;
        return this;
    }

    private void growIfNecessary(int requiredCapacity) {
        int oldCapacity = array.length;
        if (oldCapacity < requiredCapacity) {
            int newCapacity;
            if (array == EMPTY) {
                newCapacity = Math.max(requiredCapacity, INITIAL_CAPACITY);
            } else {
                newCapacity = Math.max(requiredCapacity, 2 * oldCapacity);
            }

            array = Arrays.copyOf(array, newCapacity);
        }
    }
}
