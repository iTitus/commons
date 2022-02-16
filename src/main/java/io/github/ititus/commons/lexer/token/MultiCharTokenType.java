package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.lexer.MatchResult;

import java.util.Arrays;

import static io.github.ititus.commons.lexer.MatchResult.*;

public abstract class MultiCharTokenType<T> implements TokenType<T> {

    private final String name;
    private final int[] codepoints;

    protected MultiCharTokenType(String name, int... codepoints) {
        this.name = name;
        this.codepoints = Arrays.copyOf(codepoints, codepoints.length);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public MatchResult matches(CharSequence str) {
        if (str.isEmpty()) {
            return codepoints.length == 0 ? NO_MATCH : PREFIX_ONLY_MATCH;
        }

        boolean hasPrefixMatch = false;
        for (int codepoint : codepoints) {
            MatchResult result = CharTokenType.matches(codepoint, str);
            if (result == FULL_MATCH) {
                return FULL_MATCH;
            } else if (result == PREFIX_ONLY_MATCH) {
                hasPrefixMatch = true;
            }
        }

        return hasPrefixMatch ? PREFIX_ONLY_MATCH : NO_MATCH;
    }
}
