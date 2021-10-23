package io.github.ititus.text;

import java.util.Objects;

public class SubCharSequence implements CharSequence {

    private final CharSequence sequence;
    private final int start;
    private final int end;

    public SubCharSequence(CharSequence sequence, int start, int end) {
        Objects.requireNonNull(sequence);
        if (start < 0 || end < 0 || end < start) {
            throw new IllegalArgumentException();
        }

        int length = sequence.length();
        if (start > length || end > length) {
            throw new IllegalArgumentException();
        }

        this.sequence = sequence;
        this.start = start;
        this.end = end;
    }

    @Override
    public int length() {
        return end - start;
    }

    @Override
    public char charAt(int index) {
        index += start;
        if (index < 0 || index >= end) {
            throw new IndexOutOfBoundsException();
        }

        return sequence.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        start += this.start;
        end += this.start;
        if (start < 0 || start >= this.end || end < 0 || end >= this.end) {
            throw new IndexOutOfBoundsException();
        }

        return new SubCharSequence(sequence, this.start + start, this.start + end);
    }

    @Override
    public String toString() {
        return sequence.subSequence(start, end).toString();
    }
}
