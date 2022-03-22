package io.github.ititus.commons.automaton.finite.dfa;

import io.github.ititus.commons.automaton.finite.BaseState;
import io.github.ititus.commons.automaton.finite.TargetedRule;
import io.github.ititus.commons.automaton.finite.rule.Rule;

import java.util.*;
import java.util.function.IntPredicate;

public final class State implements BaseState<State> {

    private final boolean end;
    private final String name;
    private final Map<State, TargetedRule<State>> rules;

    private State(boolean end, String name) {
        this.end = end;
        this.name = Objects.requireNonNull(name);
        this.rules = new HashMap<>();
    }

    public static State create(String name) {
        return new State(false, name);
    }

    public static State createEnd(String name) {
        return new State(true, name);
    }

    public State addSelfRule(int codepoint) {
        return addRule(this, codepoint);
    }

    public State addSelfRule(int... codepoints) {
        return addRule(this, codepoints);
    }

    public State addRule(State target, int codepoint) {
        return addRule(target, Rule.codepoint(codepoint));
    }

    public State addRule(State target, int... codepoints) {
        return addRule(target, Rule.codepoints(codepoints));
    }

    public State addSelfNotRule(int codepoint) {
        return addNotRule(this, codepoint);
    }

    public State addSelfNotRule(int... codepoints) {
        return addNotRule(this, codepoints);
    }

    public State addNotRule(State target, int codepoint) {
        return addRule(target, Rule.not(Rule.codepoint(codepoint)));
    }

    public State addNotRule(State target, int... codepoints) {
        return addRule(target, Rule.not(Rule.codepoints(codepoints)));
    }

    public State addSelfRangeRule(int start, int end) {
        return addRangeRule(this, start, end);
    }

    public State addRangeRule(State target, int start, int end) {
        return addRule(target, Rule.range(start, end));
    }

    public State addSelfRule(IntPredicate predicate) {
        return addRule(this, predicate);
    }

    public State addRule(State target, IntPredicate predicate) {
        return addRule(target, Rule.custom(predicate));
    }

    public State addSelfRule(Rule rule) {
        return addRule(this, rule);
    }

    public State addRule(State target, Rule rule) {
        return addRule(new TargetedRule<>(target, rule));
    }

    public State addRule(TargetedRule<State> rule) {
        if (rule.validCodepoints().anyMatch(cp -> accept(cp).isPresent())) {
            throw new IllegalArgumentException("non-deterministic rule not allowed");
        }

        rules.merge(rule.target(), rule, TargetedRule::merge);
        return this;
    }

    public Optional<State> accept(int codepoint) {
        return Optional.ofNullable(nullableAccept(codepoint));
    }

    public State nullableAccept(int codepoint) {
        if (codepoint < Rule.MIN_CODE_POINT || codepoint > Rule.MAX_CODE_POINT) {
            throw new IllegalArgumentException("codepoint out of bounds");
        }

        for (TargetedRule<State> r : rules.values()) {
            if (r.accepts(codepoint)) {
                return r.target();
            }
        }

        return null;
    }

    @Override
    public boolean end() {
        return end;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Collection<TargetedRule<State>> rules() {
        return Collections.unmodifiableCollection(rules.values());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof State)) {
            return false;
        }

        State state = (State) o;
        return name.equals(state.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
