package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.lexer.MatchResult;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static io.github.ititus.commons.lexer.MatchResult.*;

public abstract class MultiStringTokenType<T> implements TokenType<T> {

    private final String name;
    private final List<? extends CharSequence> tokens;

    protected MultiStringTokenType(String name, CharSequence... tokens) {
        this(name, Arrays.asList(tokens));
    }

    protected MultiStringTokenType(String name, Collection<? extends CharSequence> tokens) {
        this.name = name;
        this.tokens = tokens.stream()
                .sorted(Comparator.comparingInt(CharSequence::length).reversed())
                .toList();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public MatchResult matches(CharSequence str) {
        int len = str.length();
        if (len == 0) {
            return tokens.isEmpty() ? NO_MATCH : PREFIX_ONLY_MATCH;
        }

        boolean hasPrefixMatch = false;
        for (CharSequence token : tokens) {
            if (len > token.length()) {
                break;
            }

            switch (StringTokenType.matches(token, str)) {
                case FULL_MATCH:
                    return FULL_MATCH;
                case PREFIX_ONLY_MATCH:
                    hasPrefixMatch = true;
            }
        }

        return hasPrefixMatch ? PREFIX_ONLY_MATCH : NO_MATCH;
    }
}
