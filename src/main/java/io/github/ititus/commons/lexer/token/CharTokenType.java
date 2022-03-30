package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.lexer.MatchResult;

import static io.github.ititus.commons.lexer.MatchResult.*;

public abstract class CharTokenType<T> implements TokenType<T> {

    private final String name;
    private final char character;

    public CharTokenType(String name, char character) {
        this.name = name;
        this.character = character;
    }

    static MatchResult matches(char character, CharSequence str) {
        int length = str.length();
        if (length == 0) {
            return PREFIX_ONLY_MATCH;
        } else if (length > 1) {
            return NO_MATCH;
        }

        return str.charAt(0) == character ? FULL_MATCH : NO_MATCH;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public MatchResult matches(CharSequence str) {
        return matches(character, str);
    }
}
