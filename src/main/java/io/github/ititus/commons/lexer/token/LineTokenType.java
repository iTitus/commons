package io.github.ititus.commons.lexer.token;

public final class LineTokenType extends CharTokenType<Integer> {

    public static final LineTokenType INSTANCE = new LineTokenType();

    private LineTokenType() {
        super("LINE", '\n');
    }

    @Override
    public Integer convert(String token) {
        throw new UnsupportedOperationException();
    }
}
