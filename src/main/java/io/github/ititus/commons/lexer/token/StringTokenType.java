package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.lexer.MatchResult;

import static io.github.ititus.commons.lexer.MatchResult.*;

public class StringTokenType<T> implements TokenType<T> {

    private final String name;
    private final CharSequence token;
    private final T result;

    public StringTokenType(String name, CharSequence token, T result) {
        this.name = name;
        this.token = token;
        this.result = result;
    }

    /**
     * Ternary string matching.
     *
     * @param token the existing pattern
     * @param str   the string to match against
     * @return full match if token = str, prefix match if str is a prefix of token, and no match if there is a mismatch
     */
    static MatchResult matches(CharSequence token, CharSequence str) {
        int len1 = token.length();
        int len2 = str.length();
        if (len2 > len1) {
            return NO_MATCH;
        }

        for (int i = 0; i < len1 && i < len2; i++) {
            if (token.charAt(i) != str.charAt(i)) {
                return NO_MATCH;
            }
        }

        return len1 == len2 ? FULL_MATCH : PREFIX_ONLY_MATCH;
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
