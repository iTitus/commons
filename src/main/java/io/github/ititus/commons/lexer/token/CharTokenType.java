package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.lexer.MatchResult;

import static io.github.ititus.commons.lexer.MatchResult.*;

public abstract class CharTokenType<T> implements TokenType<T> {

    private final String name;
    private final int codepoint;

    public CharTokenType(String name, int codepoint) {
        if (!Character.isValidCodePoint(codepoint)) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.codepoint = codepoint;
    }

    static MatchResult matches(int codepoint, CharSequence str) {
        int length = str.length();
        if (length == 0) {
            return PREFIX_ONLY_MATCH;
        }

        int n = 0;
        char c1 = str.charAt(n);
        int cp = c1;
        if (Character.isHighSurrogate(c1) && n + 1 < length) {
            char c2 = str.charAt(n + 1);
            if (Character.isLowSurrogate(c2)) {
                n++;
                cp = Character.toCodePoint(c1, c2);
            }
        }

        if (length > n + 1) {
            return NO_MATCH;
        } else if (codepoint == cp) {
            return FULL_MATCH;
        } else if (Character.highSurrogate(codepoint) == cp) {
            return PREFIX_ONLY_MATCH;
        }

        return NO_MATCH;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public MatchResult matches(CharSequence str) {
        return matches(codepoint, str);
    }
}
