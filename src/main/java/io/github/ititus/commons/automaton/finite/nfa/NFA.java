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

    public Instance run(char c) {
        return newInstance().accept(c);
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

        Set<Set<State>> unmarkedStates = new HashSet<>();
        Map<Set<State>, io.github.ititus.commons.automaton.finite.dfa.State> oldStates2newStateMap = new HashMap<>();
        Map<Pair<Set<State>, Set<State>>, Set<Character>> transitions = new HashMap<>();

        Set<State> initial = this.initial.acceptEpsilon();
        unmarkedStates.add(initial);
        var dfaInitial = oldStates2newStateMap.computeIfAbsent(initial, NFA::createDFAState);

        while (!unmarkedStates.isEmpty()) {
            Set<State> nfaStates = unmarkedStates.iterator().next();
            unmarkedStates.remove(nfaStates);

            nfaStates.stream()
                    .flatMap(s -> s.rules().stream())
                    .flatMapToInt(TargetedRule::validChars)
                    .distinct()
                    .forEach(c -> {
                        Set<State> result = nfaStates.stream()
                                .flatMap(s -> s.accept((char) c).stream())
                                .flatMap(s -> s.acceptEpsilon().stream())
                                .collect(Collectors.toSet());

                        if (!result.isEmpty()) {
                            if (!oldStates2newStateMap.containsKey(result)) {
                                unmarkedStates.add(result);
                                oldStates2newStateMap.put(result, createDFAState(result));
                            }

                            transitions.computeIfAbsent(Pair.of(nfaStates, result), k -> new HashSet<>()).add((char) c);
                        }
                    });
        }

        transitions.forEach((p, cs) -> oldStates2newStateMap.get(p.a()).addRule(oldStates2newStateMap.get(p.b()), cs::contains));

        return new DFA(dfaInitial);
    }
}
