package io.github.ititus.commons.automaton.finite.nfa;

import io.github.ititus.commons.automaton.finite.TargetedRule;
import io.github.ititus.commons.automaton.finite.dfa.DFA;
import io.github.ititus.commons.data.pair.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record NFA(State initial) {

    public NFA() {
        this(null);
    }

    private static io.github.ititus.commons.automaton.finite.dfa.State createDFAState(Set<State> s) {
        String name = s.size() == 1 ? s.iterator().next().name() : s.stream().map(State::name).sorted().collect(Collectors.joining(", ", "{", "}"));
        return s.stream().anyMatch(State::end) ? io.github.ititus.commons.automaton.finite.dfa.State.createEnd(name) : io.github.ititus.commons.automaton.finite.dfa.State.create(name);
    }

    public Instance newInstance() {
        return initial != null ? Instance.create(initial) : Instance.invalid();
    }

    public Instance runEpsilon() {
        return newInstance().acceptEpsilon();
    }

    public Instance run(CharSequence input) {
        return newInstance().accept(input);
    }

    public Instance run(int codepoint) {
        return newInstance().accept(codepoint);
    }

    public Set<State> states() {
        if (initial == null) {
            return Set.of();
        }

        Set<State> states = new HashSet<>();
        Deque<State> q = new LinkedList<>();
        q.addFirst(initial);
        while (!q.isEmpty()) {
            State s = q.removeFirst();
            if (states.add(s)) {
                Stream.concat(
                                s.epsilonTransitions().stream(),
                                s.rules().stream().map(TargetedRule::target)
                        )
                        .forEach(q::addFirst);
            }
        }

        return Set.copyOf(states);
    }

    public DFA toDFA() {
        if (this.initial == null) {
            return new DFA();
        }

        // TODO: optimize this

        Set<Set<State>> markedStates = new HashSet<>();
        Set<Set<State>> unmarkedStates = new HashSet<>();
        Map<Set<State>, io.github.ititus.commons.automaton.finite.dfa.State> state2stateMap = new HashMap<>();
        Map<Pair<Set<State>, Set<State>>, Set<Integer>> transitions = new HashMap<>();

        Set<State> initial = this.initial.acceptEpsilon();
        unmarkedStates.add(initial);
        var dfaInitial = state2stateMap.computeIfAbsent(initial, NFA::createDFAState);

        while (!unmarkedStates.isEmpty()) {
            Set<State> dfaState = unmarkedStates.iterator().next();
            unmarkedStates.remove(dfaState);
            markedStates.add(dfaState);

            dfaState.stream()
                    .flatMap(s -> s.rules().stream())
                    .flatMapToInt(TargetedRule::validCodepoints)
                    .distinct()
                    .forEach(cp -> {
                        Set<State> result = dfaState.stream()
                                .flatMap(s -> s.accept(cp).stream())
                                .flatMap(s -> s.acceptEpsilon().stream())
                                .collect(Collectors.toSet());

                        if (!result.isEmpty()) {
                            if (!markedStates.contains(result) && unmarkedStates.add(result)) {
                                state2stateMap.computeIfAbsent(result, NFA::createDFAState);
                            }

                            transitions.computeIfAbsent(Pair.of(dfaState, result), k -> new HashSet<>()).add(cp);
                        }
                    });
        }

        transitions.forEach((p, cps) -> state2stateMap.get(p.a()).addRule(state2stateMap.get(p.b()), cps::contains));

        return new DFA(dfaInitial);
    }
}
