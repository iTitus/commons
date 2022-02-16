package io.github.ititus.commons.lexer.token;

public record Token<T>(
        TokenType<T> type,
        T token
) {

    @Override
    public String toString() {
        return type.name() + ": " + token;
    }
}
