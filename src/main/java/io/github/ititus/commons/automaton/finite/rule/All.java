package io.github.ititus.commons.automaton.finite.rule;

import java.util.stream.IntStream;

final class All implements Rule {

    static final All INSTANCE = new All();

    private All() {
        super();
    }

    @Override
    public String describe() {
        return "<all>";
    }

    @Override
    public boolean accepts(int codepoint) {
        return true;
    }

    @Override
    public IntStream validCodepoints() {
        return IntStream.rangeClosed(Rule.MIN_CODE_POINT, Rule.MAX_CODE_POINT);
    }

    @Override
    public int validCodepointCount() {
        return Rule.CODE_POINT_COUNT;
    }
}
