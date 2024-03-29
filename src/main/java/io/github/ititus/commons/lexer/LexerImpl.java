package io.github.ititus.commons.lexer;

import io.github.ititus.commons.lexer.token.LineTokenType;
import io.github.ititus.commons.lexer.token.Token;
import io.github.ititus.commons.lexer.token.TokenType;
import io.github.ititus.commons.text.SubCharSequence;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.BitSet;
import java.util.List;

final class LexerImpl implements Lexer {

    private final List<TokenType<?>> tokenTypes;
    private final boolean hasLineTokenType;
    private final char[] acceptedNewLines;

    private final PushbackReader reader;
    private final StringBuilder buffer;
    private final BitSet validTokens;

    private boolean empty;
    private int line;
    private TokenType<?> currentTokenCandidate;
    private int currentTokenEnd;

    LexerImpl(Reader reader, char[] acceptedNewLines, List<TokenType<?>> tokenTypes) {
        this.tokenTypes = tokenTypes;
        this.hasLineTokenType = tokenTypes.stream().anyMatch(t -> t instanceof LineTokenType);
        this.acceptedNewLines = acceptedNewLines;

        this.reader = new PushbackReader(reader, 1);
        this.buffer = new StringBuilder();
        this.validTokens = new BitSet(tokenTypes.size());

        this.empty = true;
        this.line = 1;
        resetForNextToken();
    }

    @Override
    public Token<?> nextToken() throws IOException {
        outer:
        while (true) {
            for (int length = 1, bufferLength = buffer.length(); length <= bufferLength; length++) {
                CharSequence bufferView = SubCharSequence.of(buffer, 0, length);
                if (checkTokens(bufferView)) {
                    Token<?> next = endCurrentToken(bufferView);
                    if (next == null || !next.type().ignore()) {
                        return next;
                    }

                    continue outer;
                }
            }

            while (true) {
                int cOrEof = nextChar();
                if (cOrEof < 0) {
                    if (buffer.isEmpty()) {
                        return null;
                    } else if (currentTokenCandidate == null || validTokens.cardinality() == 0) {
                        throw new LexerException("unknown token in line " + line + ": '" + buffer + "'");
                    }

                    Token<?> next = endCurrentToken(buffer);
                    if (next == null || next.type().ignore()) {
                        return null;
                    }

                    return next;
                }

                char c = (char) cOrEof;
                buffer.append(c);

                if (empty) {
                    empty = false;
                    if (hasLineTokenType) {
                        return Token.create(LineTokenType.INSTANCE, line);
                    }
                }

                if (checkTokens(buffer)) {
                    Token<?> next = endCurrentToken(buffer);
                    if (next == null || !next.type().ignore()) {
                        return next;
                    }

                    continue outer;
                }
            }
        }
    }

    private boolean checkTokens(CharSequence buffer) {
        boolean changedTokenCandidate = false;
        for (int i = 0, len = tokenTypes.size(); i < len; i++) {
            if (!validTokens.get(i)) {
                continue;
            }

            // TODO: use some king of stateful matcher, so we don't have to recalculate the match with the whole string
            TokenType<?> tokenType = tokenTypes.get(i);
            MatchResult result = tokenType.matches(buffer);
            if (result == MatchResult.NO_MATCH) {
                validTokens.clear(i);
            } else if (!changedTokenCandidate && result == MatchResult.FULL_MATCH) {
                currentTokenCandidate = tokenType;
                currentTokenEnd = buffer.length();
                changedTokenCandidate = true;
            }
        }

        if (validTokens.cardinality() == 0) {
            if (currentTokenCandidate == null) {
                throw new LexerException("unknown token in line " + line + ": '" + buffer + "'");
            }

            return true;
        }

        return false;
    }

    private int nextChar() throws IOException {
        int c = reader.read();
        if (c < 0) {
            return -1;
        }

        if (c == '\r') {
            int next = reader.read();
            if (next != '\n') {
                reader.unread(next);
            }

            return '\n';
        } else if (c == '\n') {
            return '\n';
        } else {
            for (char acceptedNewLine : acceptedNewLines) {
                if (c == acceptedNewLine) {
                    return '\n';
                }
            }
        }

        return c;
    }

    @SuppressWarnings("unchecked")
    private Token<?> endCurrentToken(CharSequence buffer) {
        Token<?> token;
        if (currentTokenCandidate != null) {
            if (currentTokenCandidate instanceof LineTokenType) {
                line++;
                token = Token.create((TokenType<? super Integer>) currentTokenCandidate, line);
            } else {
                String tokenText = buffer.subSequence(0, currentTokenEnd).toString();
                line += (int) tokenText.chars().filter(c -> c == '\n').count();
                token = Token.createRaw((TokenType<? super Object>) currentTokenCandidate, tokenText);
            }
        } else {
            token = null;
        }

        resetForNextToken();
        return token;
    }

    private void resetForNextToken() {
        if (currentTokenEnd < buffer.length()) {
            buffer.delete(0, currentTokenEnd);
        } else {
            buffer.setLength(0);
        }

        validTokens.set(0, tokenTypes.size());
        currentTokenCandidate = null;
        currentTokenEnd = 0;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
