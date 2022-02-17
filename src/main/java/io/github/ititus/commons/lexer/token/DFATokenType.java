package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.automaton.finite.dfa.DFA;
import io.github.ititus.commons.automaton.finite.dfa.Instance;
import io.github.ititus.commons.lexer.MatchResult;

import static io.github.ititus.commons.lexer.MatchResult.*;

public abstract class DFATokenType<T> implements TokenType<T> {

    private final String name;
    private final DFA dfa;

    public DFATokenType(String name, DFA dfa) {
        this.name = name;
        this.dfa = dfa/*.minimize()*/; // TODO: enable minimization once properly optimized
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public MatchResult matches(CharSequence str) {
        Instance instance = dfa.run(str);
        if (instance.isInvalid()) {
            return NO_MATCH;
        } else if (instance.isFinished()) {
            return FULL_MATCH;
        }

        return PREFIX_ONLY_MATCH;
    }
}
