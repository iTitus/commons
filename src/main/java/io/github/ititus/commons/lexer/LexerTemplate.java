package io.github.ititus.commons.lexer;

import io.github.ititus.commons.lexer.token.TokenType;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public final class LexerTemplate {

    private final List<Character> acceptedNewLines = new ArrayList<>();
    private final List<TokenType<?>> tokenTypes = new ArrayList<>();

    private LexerTemplate() {
    }

    public static LexerTemplate create() {
        return new LexerTemplate();
    }

    public Lexer lexer(Reader reader) {
        char[] acceptedNewLines = new char[this.acceptedNewLines.size()];
        for (int i = 0; i < acceptedNewLines.length; i++) {
            acceptedNewLines[i] = this.acceptedNewLines.get(i);
        }

        return new LexerImpl(reader, acceptedNewLines, List.copyOf(tokenTypes));
    }

    public LexerTemplate addNewLine(char newLine) {
        acceptedNewLines.add(newLine);
        return this;
    }

    public LexerTemplate addTokenType(TokenType<?> tokenType) {
        tokenTypes.add(tokenType);
        return this;
    }
}
