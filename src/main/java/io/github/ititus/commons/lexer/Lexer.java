package io.github.ititus.commons.lexer;

import io.github.ititus.commons.lexer.token.Token;

import java.io.Closeable;
import java.io.IOException;

public interface Lexer extends Closeable {

    Token<?> nextToken() throws IOException;

}
