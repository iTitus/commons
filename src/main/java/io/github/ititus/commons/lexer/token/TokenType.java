package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.lexer.MatchResult;

public interface TokenType<T> {

    String name();

    MatchResult matches(CharSequence str);

    default boolean ignore() {
        return false;
    }

    T convert(String token);

}
