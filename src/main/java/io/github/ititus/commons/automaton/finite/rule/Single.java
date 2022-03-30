package io.github.ititus.commons.automaton.finite.rule;

import io.github.ititus.commons.automaton.finite.DotUtil;

import java.util.stream.IntStream;

record Single(char character) implements Rule {

    @Override
    public String describe() {
        return DotUtil.toStringAsEscape(character);
    }

    @Override
    public boolean accepts(char c) {
        return c == this.character;
    }

    @Override
    public IntStream validChars() {
        return IntStream.of(character);
    }

    @Override
    public int validCharCount() {
        return 1;
    }
}
