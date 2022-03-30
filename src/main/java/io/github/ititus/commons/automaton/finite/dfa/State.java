package io.github.ititus.commons.automaton.finite.dfa;

import io.github.ititus.commons.automaton.finite.BaseState;
import io.github.ititus.commons.automaton.finite.TargetedRule;
import io.github.ititus.commons.automaton.finite.rule.Rule;
import io.github.ititus.commons.function.CharPredicate;

import java.util.*;

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

    public State addSelfRule(char c) {
        return addRule(this, c);
    }

    public State addSelfRule(char... cs) {
        return addRule(this, cs);
    }

    public State addRule(State target, char c) {
        return addRule(target, Rule.character(c));
    }

    public State addRule(State target, char... cs) {
        return addRule(target, Rule.characters(cs));
    }

    public State addSelfNotRule(char c) {
        return addNotRule(this, c);
    }

    public State addSelfNotRule(char... cs) {
        return addNotRule(this, cs);
    }

    public State addNotRule(State target, char c) {
        return addRule(target, Rule.not(Rule.character(c)));
    }

    public State addNotRule(State target, char... cs) {
        return addRule(target, Rule.not(Rule.characters(cs)));
    }

    public State addSelfRangeRule(char start, char end) {
        return addRangeRule(this, start, end);
    }

    public State addRangeRule(State target, char start, char end) {
        return addRule(target, Rule.range(start, end));
    }

    public State addSelfRule(CharPredicate predicate) {
        return addRule(this, predicate);
    }

    public State addRule(State target, CharPredicate predicate) {
        return addRule(target, Rule.custom(predicate));
    }

    public State addSelfRule(Rule rule) {
        return addRule(this, rule);
    }

    public State addRule(State target, Rule rule) {
        return addRule(new TargetedRule<>(target, rule));
    }

    public State addRule(TargetedRule<State> rule) {
        if (rule.validChars().anyMatch(c -> accept((char) c).isPresent())) {
            throw new IllegalArgumentException("non-deterministic rule not allowed");
        }

        rules.merge(rule.target(), rule, TargetedRule::merge);
        return this;
    }

    public Optional<State> accept(char c) {
        return Optional.ofNullable(nullableAccept(c));
    }

    public State nullableAccept(char c) {
        for (TargetedRule<State> r : rules.values()) {
            if (r.accepts(c)) {
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
