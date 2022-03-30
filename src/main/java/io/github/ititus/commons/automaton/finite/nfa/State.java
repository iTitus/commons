package io.github.ititus.commons.automaton.finite.nfa;

import io.github.ititus.commons.automaton.finite.BaseState;
import io.github.ititus.commons.automaton.finite.TargetedRule;
import io.github.ititus.commons.automaton.finite.rule.Rule;
import io.github.ititus.commons.function.CharPredicate;

import java.util.*;
import java.util.stream.Collectors;

public final class State implements BaseState<State> {

    private final boolean end;
    private final String name;
    private final Map<State, TargetedRule<State>> rules;
    private final Set<State> epsilonTransitions;
    private Set<State> cachedEpsilonClosure;

    private State(boolean end, String name) {
        this.end = end;
        this.name = Objects.requireNonNull(name);
        this.rules = new HashMap<>();
        this.epsilonTransitions = new HashSet<>();
        this.cachedEpsilonClosure = null;
    }

    public static State create(String name) {
        return new State(false, name);
    }

    public static State createEnd(String name) {
        return new State(true, name);
    }

    public State addSelfEpsilonTransition() {
        return addEpsilonTransition(this);
    }

    public State addEpsilonTransition(State target) {
        epsilonTransitions.add(Objects.requireNonNull(target));
        cachedEpsilonClosure = null;
        return this;
    }

    public State addSelfRule(char... cs) {
        return addRule(this, cs);
    }

    public State addRule(State target, char... cs) {
        return addRule(target, Rule.characters(cs));
    }

    public State addSelfNotRule(char... cs) {
        return addNotRule(this, cs);
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
        rules.merge(rule.target(), rule, TargetedRule::merge);
        cachedEpsilonClosure = null;
        return this;
    }

    public Set<State> acceptEpsilon() {
        if (cachedEpsilonClosure == null) {
            Set<State> epsilonClosure = new HashSet<>();
            Deque<State> q = new LinkedList<>();
            q.addFirst(this);
            while (!q.isEmpty()) {
                State s = q.removeFirst();
                if (epsilonClosure.add(s)) {
                    for (State e : s.epsilonTransitions) {
                        q.addFirst(e);
                    }
                }
            }

            cachedEpsilonClosure = Set.copyOf(epsilonClosure);
        }

        return cachedEpsilonClosure;
    }

    public Set<State> accept(char c) {
        return rules.values().stream()
                .filter(r -> r.accepts(c))
                .map(TargetedRule::target)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public boolean end() {
        return end;
    }

    @Override
    public String name() {
        return name;
    }

    public Set<State> epsilonTransitions() {
        return Collections.unmodifiableSet(epsilonTransitions);
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
