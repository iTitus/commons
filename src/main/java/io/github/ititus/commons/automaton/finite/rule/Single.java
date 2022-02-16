package io.github.ititus.commons.automaton.finite.rule;

import io.github.ititus.commons.automaton.finite.DotUtil;

import java.util.stream.IntStream;

final class Single implements Rule {

    private final int codepoint;

    Single(int codepoint) {
        super();
        this.codepoint = codepoint;
    }

    @Override
    public String describe() {
        return DotUtil.toStringAsEscape(codepoint);
    }

    @Override
    public boolean accepts(int codepoint) {
        return codepoint == this.codepoint;
    }

    @Override
    public IntStream validCodepoints() {
        return IntStream.of(codepoint);
    }

    @Override
    public int validCodepointCount() {
        return 1;
    }
}
