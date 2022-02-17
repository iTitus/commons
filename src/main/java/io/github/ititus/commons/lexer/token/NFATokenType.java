package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.automaton.finite.nfa.Instance;
import io.github.ititus.commons.automaton.finite.nfa.NFA;
import io.github.ititus.commons.lexer.MatchResult;

import static io.github.ititus.commons.lexer.MatchResult.*;

public abstract class NFATokenType<T> implements TokenType<T> {

    private final String name;
    private final NFA nfa;

    public NFATokenType(String name, NFA nfa) {
        this.name = name;
        this.nfa = nfa; // TODO: convert to dfa and minimize once properly optimized
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public MatchResult matches(CharSequence str) {
        Instance instance = nfa.run(str);
        if (instance.isInvalid()) {
            return NO_MATCH;
        } else if (instance.isFinished()) {
            return FULL_MATCH;
        }

        return PREFIX_ONLY_MATCH;
    }
}
