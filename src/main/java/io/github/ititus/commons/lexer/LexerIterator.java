package io.github.ititus.commons.lexer;

import io.github.ititus.commons.lexer.token.Token;

import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class LexerIterator implements Iterator<Token<?>>, Closeable {

    private final Lexer lexer;
    private Token<?> cache;
    private boolean check;

    public LexerIterator(Lexer lexer) {
        this.lexer = lexer;
        this.check = true;
    }

    public Lexer lexer() {
        return lexer;
    }

    public boolean hasNext() {
        if (check) {
            try {
                cache = lexer.nextToken();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }

            check = false;
        }

        return cache != null;
    }

    public Token<?> next() {
        if (!hasNext()) {
            throw new NoSuchElementException("unexpected eof");
        }

        check = true;
        Token<?> cache = this.cache;
        this.cache = null;
        return cache;
    }

    @Override
    public void close() throws IOException {
        lexer.close();
    }
}
