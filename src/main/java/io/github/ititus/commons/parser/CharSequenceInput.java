package io.github.ititus.commons.parser;

import io.github.ititus.commons.text.SubCharSequence;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

class CharSequenceInput extends AbstractCharInput {

    private final CharSequence s;
    private final int pos;

    CharSequenceInput(CharSequence s) {
        this(s, 0);
    }

    CharSequenceInput(CharSequence s, int pos) {
        Objects.requireNonNull(s);
        if (pos < 0 || pos > s.length()) {
            throw new IllegalArgumentException();
        }

        this.s = s;
        this.pos = pos;
    }

    @Override
    public boolean isEmpty() {
        return seq().isEmpty();
    }

    @Override
    public int pos() {
        return pos;
    }

    @Override
    public List<Character> get(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        } else if (n > s.length()) {
            throw new NoSuchElementException();
        }

        return seq().chars().limit(n).mapToObj(c -> (char) c).toList();
    }

    @Override
    public CharSequenceInput next(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        } else if (pos + n > s.length()) {
            throw new NoSuchElementException();
        }

        return new CharSequenceInput(s, pos + n);
    }

    @Override
    public CharSequence seq() {
        return SubCharSequence.of(s, pos, s.length());
    }
}
