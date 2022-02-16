package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.lexer.MatchResult;

import java.util.List;

import static io.github.ititus.commons.lexer.MatchResult.*;

public abstract class MultiStringTokenType<T> implements TokenType<T> {

    private final String name;
    private final List<String> tokens;

    protected MultiStringTokenType(String name, String... tokens) {
        this.name = name;
        this.tokens = List.of(tokens);
    }

    protected MultiStringTokenType(String name, List<String> tokens) {
        this.name = name;
        this.tokens = List.copyOf(tokens);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public MatchResult matches(CharSequence str) {
        if (str.isEmpty()) {
            return tokens.isEmpty() ? NO_MATCH : PREFIX_ONLY_MATCH;
        }

        boolean hasPrefixMatch = false;
        for (String token : tokens) {
            MatchResult result = StringTokenType.matches(token, str);
            if (result == FULL_MATCH) {
                return FULL_MATCH;
            } else if (result == PREFIX_ONLY_MATCH) {
                hasPrefixMatch = true;
            }
        }

        return hasPrefixMatch ? PREFIX_ONLY_MATCH : NO_MATCH;
    }
}
