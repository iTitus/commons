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
    public boolean accepts(char c) {
        return true;
    }

    @Override
    public IntStream validChars() {
        return IntStream.rangeClosed(Rule.MIN_VALUE, Rule.MAX_VALUE);
    }

    @Override
    public int validCharCount() {
        return Rule.CHAR_COUNT;
    }
}
