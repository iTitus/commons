package io.github.ititus.commons.automaton.finite.rule;

import java.util.stream.IntStream;

final class None implements Rule {

    static final None INSTANCE = new None();

    private None() {
        super();
    }

    @Override
    public String describe() {
        return "<none>";
    }

    @Override
    public boolean accepts(char c) {
        return false;
    }

    @Override
    public IntStream validChars() {
        return IntStream.empty();
    }

    @Override
    public int validCharCount() {
        return 0;
    }
}
