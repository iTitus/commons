package io.github.ititus.commons.automaton.finite.rule;

import io.github.ititus.commons.automaton.finite.DotUtil;

import java.util.stream.IntStream;

final class Range implements Rule {

    private final int start;
    private final int end;

    Range(int start, int end) {
        super();
        if (start > end) {
            throw new IllegalArgumentException();
        }

        this.start = start;
        this.end = end;
    }

    @Override
    public String describe() {
        return DotUtil.toStringAsEscape(start) + "-" + DotUtil.toStringAsEscape(end);
    }

    @Override
    public boolean accepts(int codepoint) {
        return start <= codepoint && codepoint <= end;
    }

    @Override
    public IntStream validCodepoints() {
        return IntStream.rangeClosed(start, end);
    }

    @Override
    public int validCodepointCount() {
        return end - start + 1;
    }
}
