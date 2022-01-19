package io.github.ititus.commons.text;

import java.util.Objects;

public final class SubCharSequence implements CharSequence {

    private final CharSequence sequence;
    private final int start;
    private final int end;

    private SubCharSequence(CharSequence sequence, int start, int end) {
        Objects.requireNonNull(sequence);
        if (start < 0 || end < start) {
            throw new IllegalArgumentException();
        }

        int length = sequence.length();
        if (end > length) {
            throw new IllegalArgumentException();
        }

        this.sequence = sequence;
        this.start = start;
        this.end = end;
    }

    public static CharSequence of(CharSequence sequence, int start, int end) {
        Objects.requireNonNull(sequence);
        if (start < 0 || end < start) {
            throw new IllegalArgumentException();
        }

        int length = sequence.length();
        if (end > length) {
            throw new IllegalArgumentException();
        }

        if (length == 0 || start == end) {
            return "";
        } else if (start == 0 && end == length) {
            return sequence;
        } else if (sequence instanceof SubCharSequence) {
            SubCharSequence subseq = (SubCharSequence) sequence;
            return new SubCharSequence(subseq.sequence, start + subseq.start, end + subseq.start);
        }

        return new SubCharSequence(sequence, start, end);
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
        return of(this, start, end);
    }

    @Override
    public String toString() {
        return sequence.subSequence(start, end).toString();
    }
}
