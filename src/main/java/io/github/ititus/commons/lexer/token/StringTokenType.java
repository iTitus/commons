package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.lexer.MatchResult;

import static io.github.ititus.commons.lexer.MatchResult.*;

public class StringTokenType<T> implements TokenType<T> {

    private final String name;
    private final String token;
    private final T result;

    public StringTokenType(String name, String token, T result) {
        this.name = name;
        this.token = token;
        this.result = result;
    }

    @SuppressWarnings("Duplicates")
    static MatchResult matches(CharSequence token, CharSequence str) {
        int len1 = token.length();
        int len2 = str.length();
        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            if (token.charAt(i++) != str.charAt(j++)) {
                return NO_MATCH;
            }
        }

        if (i == len1 && j == len2) {
            return FULL_MATCH;
        }

        return j < len2 ? NO_MATCH : PREFIX_ONLY_MATCH;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public MatchResult matches(CharSequence str) {
        return matches(token, str);
    }

    @Override
    public T convert(String token) {
        return result;
    }
}
