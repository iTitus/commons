package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.lexer.MatchResult;

import java.util.Arrays;

import static io.github.ititus.commons.lexer.MatchResult.*;

public abstract class MultiCharTokenType<T> implements TokenType<T> {

    private final String name;
    private final char[] characters;

    protected MultiCharTokenType(String name, char... characters) {
        this.name = name;
        this.characters = Arrays.copyOf(characters, characters.length);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public MatchResult matches(CharSequence str) {
        int length = str.length();
        if (length == 0) {
            return characters.length == 0 ? NO_MATCH : PREFIX_ONLY_MATCH;
        } else if (length > 1) {
            return NO_MATCH;
        }

        char existingChar = str.charAt(0);
        for (char c : characters) {
            if (existingChar == c) {
                return FULL_MATCH;
            }
        }

        return NO_MATCH;
    }
}
