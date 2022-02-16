package io.github.ititus.commons.automaton.finite.nfa;

import io.github.ititus.commons.automaton.finite.Rule;
import io.github.ititus.commons.automaton.finite.dfa.DFA;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record NFA(State initial) {

    public NFA() {
        this(null);
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
                                s.rules().stream().map(Rule::target)
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

        Map<Set<State>, Boolean> dfaStates = new HashMap<>();
        Map<Set<State>, Map<Integer, Set<State>>> dfaTransitions = new HashMap<>();

        Set<State> initial = Set.copyOf(this.initial.acceptEpsilon());
        dfaStates.put(initial, false);

        while (true) {
            Set<State> dfaState = null;
            for (var e : dfaStates.entrySet()) {
                if (!e.getValue()) {
                    dfaState = e.getKey();
                    e.setValue(true);
                    break;
                }
            }

            if (dfaState == null) {
                break;
            }

            Set<State> finalDfaState = dfaState;
            finalDfaState.stream()
                    .flatMap(s -> s.rules().stream())
                    .flatMapToInt(Rule::validCodepoints)
                    .distinct()
                    .forEach(cp -> {
                        Set<State> result = finalDfaState.stream()
                                .flatMap(s -> s.accept(cp).stream())
                                .flatMap(s -> s.acceptEpsilon().stream())
                                .collect(Collectors.toUnmodifiableSet());
                        if (!result.isEmpty()) {
                            dfaStates.putIfAbsent(result, false);
                            dfaTransitions.computeIfAbsent(finalDfaState, k -> new HashMap<>()).put(cp, result);
                        }
                    });
        }

        Map<Set<State>, io.github.ititus.commons.automaton.finite.dfa.State> state2stateMap = new HashMap<>();
        dfaStates.forEach((s, marked) -> state2stateMap.computeIfAbsent(s, k -> {
            String name = s.stream().map(State::name).sorted().collect(Collectors.joining(", ", "{", "}"));
            return s.stream().anyMatch(State::end) ? io.github.ititus.commons.automaton.finite.dfa.State.createEnd(name) : io.github.ititus.commons.automaton.finite.dfa.State.create(name);
        }));

        dfaTransitions.forEach((state, transitions) ->
                transitions.forEach((cp, target) ->
                        state2stateMap.get(state).addRule(state2stateMap.get(target), cp)));

        return new DFA(state2stateMap.get(initial));
    }
}
