package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.automaton.finite.nfa.NFA;

public abstract class NFATokenType<T> extends DFATokenType<T> {

    public NFATokenType(String name, NFA nfa) {
        super(name, nfa.toDFA());
    }
}
