package io.github.ititus.commons.automaton.finite.rule;

import io.github.ititus.commons.automaton.finite.DotUtil;

import java.util.stream.IntStream;

record Range(char start, char end) implements Rule {

    Range {
        if (start > end) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String describe() {
        return DotUtil.toStringAsEscape(start) + "-" + DotUtil.toStringAsEscape(end);
    }

    @Override
    public boolean accepts(char c) {
        return start <= c && c <= end;
    }

    @Override
    public IntStream validChars() {
        return IntStream.rangeClosed(start, end);
    }

    @Override
    public int validCharCount() {
        return end - start + 1;
    }
}
