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
            char ch1 = token.charAt(i++);
            int cp1 = ch1;
            if (Character.isHighSurrogate(ch1) && i < len1) {
                char next = token.charAt(i);
                if (Character.isLowSurrogate(next)) {
                    i++;
                    cp1 = Character.toCodePoint(ch1, next);
                }
            }

            char ch2 = str.charAt(j++);
            int cp2 = ch2;
            if (Character.isHighSurrogate(ch2) && j < len2) {
                char next = str.charAt(j);
                if (Character.isLowSurrogate(next)) {
                    j++;
                    cp2 = Character.toCodePoint(ch1, next);
                }
            }

            if (cp1 != cp2) {
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
