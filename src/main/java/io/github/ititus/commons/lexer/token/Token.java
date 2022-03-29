package io.github.ititus.commons.lexer.token;

import java.util.Objects;

public final class Token<T> {

    private final TokenType<T> type;
    private String raw;
    private T token;

    private Token(TokenType<T> type, String raw, T token) {
        this.type = type;
        this.raw = raw;
        this.token = token;
    }

    public static <T> Token<T> createRaw(TokenType<T> type, String raw) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(raw);
        return new Token<>(type, raw, null);
    }

    public static <T> Token<T> create(TokenType<T> type, T token) {
        Objects.requireNonNull(type);
        return new Token<>(type, null, token);
    }

    public TokenType<T> type() {
        return type;
    }

    public T token() {
        if (raw != null) {
            token = type.convert(raw);
            raw = null;
        }

        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Token)) {
            return false;
        }

        Token<?> token1 = (Token<?>) o;
        return type.equals(token1.type) && token().equals(token1.token());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, token());
    }

    @Override
    public String toString() {
        return type.name() + ": " + token();
    }
}
