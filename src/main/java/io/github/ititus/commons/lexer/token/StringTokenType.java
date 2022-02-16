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

    static MatchResult matches(CharSequence token, CharSequence str) {
        int[] tokenCodepoints = token.codePoints().toArray();
        int[] strCodepoints = str.codePoints().toArray();

        int tokenLength = tokenCodepoints.length;
        int strLength = strCodepoints.length;
        if (strLength > tokenLength) {
            return NO_MATCH;
        }

        for (int i = 0; i < strLength; i++) {
            if (tokenCodepoints[i] != strCodepoints[i]) {
                return NO_MATCH;
            }
        }

        return strLength == tokenLength ? FULL_MATCH : PREFIX_ONLY_MATCH;
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
