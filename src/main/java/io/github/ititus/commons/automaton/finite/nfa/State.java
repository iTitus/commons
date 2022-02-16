package io.github.ititus.commons.automaton.finite.nfa;

import io.github.ititus.commons.automaton.finite.BaseState;
import io.github.ititus.commons.automaton.finite.Rule;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public final class State implements BaseState<State> {

    private final boolean end;
    private final String name;
    private final Map<State, Rule<State>> rules;
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

    public State addRule(State target, int codepoint) {
        return addRule(new Rule.Simple<>(target, codepoint));
    }

    public State addSelfRule(int codepoint) {
        return addRule(this, codepoint);
    }

    public State addNotRule(State target, int codepoint) {
        return addRule(Rule.Not.create(target, new Rule.Simple<>(target, codepoint)));
    }

    public State addSelfNotRule(int codepoint) {
        return addNotRule(this, codepoint);
    }

    public State addRule(State target, int... codepoints) {
        for (int codepoint : codepoints) {
            addRule(target, codepoint);
        }

        return this;
    }

    public State addSelfRule(int... codepoints) {
        return addRule(this, codepoints);
    }

    public State addNotRule(State target, int... codepoints) {
        for (int codepoint : codepoints) {
            addNotRule(target, codepoint);
        }

        return this;
    }

    public State addSelfNotRule(int... codepoints) {
        return addNotRule(this, codepoints);
    }

    public State addRangeRule(State target, int start, int end) {
        return addRule(new Rule.Range<>(target, start, end));
    }

    public State addSelfRangeRule(int start, int end) {
        return addRangeRule(this, start, end);
    }

    public State addRule(State target, String description, IntPredicate predicate) {
        return addRule(new Rule.Custom<>(target, description, predicate));
    }

    public State addSelfRule(String description, IntPredicate predicate) {
        return addRule(this, description, predicate);
    }

    public State addRule(State target, Function<State, Rule<State>> ruleFactory) {
        return addRule(ruleFactory.apply(target));
    }

    public State addSelfRule(Function<State, Rule<State>> ruleFactory) {
        return addRule(this, ruleFactory);
    }

    public State addRule(Rule<State> rule) {
        rules.merge(rule.target(), rule, Rule::merge);
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

    public Set<State> accept(int codepoint) {
        return rules.values().stream()
                .filter(r -> r.accepts(codepoint))
                .map(Rule::target)
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
    public Collection<Rule<State>> rules() {
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
}
