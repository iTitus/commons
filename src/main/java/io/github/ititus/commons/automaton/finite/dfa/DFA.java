package io.github.ititus.commons.automaton.finite.dfa;

import io.github.ititus.commons.automaton.finite.TargetedRule;
import io.github.ititus.commons.data.pair.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record DFA(State initial) {

    public DFA() {
        this(null);
    }

    public Instance newInstance() {
        return initial != null ? Instance.create(initial) : Instance.invalid();
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
                s.rules().stream()
                        .map(TargetedRule::target)
                        .forEach(q::addFirst);
            }
        }

        return Set.copyOf(states);
    }

    public DFA minimize() {
        if (initial == null) {
            return this;
        }

        // TODO: optimize this

        Set<State> allStates = states();
        Set<State> endStates = allStates.stream().filter(State::end).collect(Collectors.toUnmodifiableSet());
        Set<State> nonEndStates = allStates.stream().filter(s -> !s.end()).collect(Collectors.toUnmodifiableSet());
        Set<Set<State>> partition = endStates.isEmpty() ? Set.of(nonEndStates) : nonEndStates.isEmpty() ? Set.of(endStates) : Set.of(endStates, nonEndStates);

        while (true) {
            Set<Set<State>> newPartition = new HashSet<>();
            for (Set<State> group : partition) {
                if (group.isEmpty()) {
                    throw new IllegalStateException();
                }

                List<Set<State>> newGroups = new ArrayList<>();
                for (State state : group) {
                    Set<State> groupToAdd = null;
                    outer:
                    for (Set<State> existingGroup : newGroups) {
                        State existingGroupRepresentative = existingGroup.stream().findAny().orElseThrow();
                        var validCodepointsIterator = Stream.concat(state.rules().stream(), existingGroupRepresentative.rules().stream())
                                .flatMapToInt(TargetedRule::validCodepoints)
                                .distinct()
                                .iterator();
                        while (validCodepointsIterator.hasNext()) {
                            int cp = validCodepointsIterator.nextInt();
                            Set<Set<State>> finalPartition = partition;
                            Optional<Set<State>> targetGroup1 = state.accept(cp).map(s -> finalPartition.stream().filter(g -> g.contains(s)).findAny().orElseThrow());
                            Optional<Set<State>> targetGroup2 = existingGroupRepresentative.accept(cp).map(s -> finalPartition.stream().filter(g -> g.contains(s)).findAny().orElseThrow());
                            if (!targetGroup1.equals(targetGroup2)) {
                                continue outer;
                            }
                        }

                        groupToAdd = existingGroup;
                        break;
                    }

                    if (groupToAdd != null) {
                        groupToAdd.add(state);
                    } else {
                        newGroups.add(new HashSet<>(List.of(state)));
                    }
                }

                newPartition.addAll(newGroups);
            }

            if (newPartition.equals(partition)) {
                break;
            }

            partition = newPartition;
        }

        State minInitial = null;
        List<Pair<State, State>> minStates = new ArrayList<>();
        Map<State, Pair<State, State>> minStatesByStates = new HashMap<>();
        for (Set<State> group : partition) {
            State representative = group.stream().min(Comparator.comparing(State::name)).orElseThrow();
            State minState = representative.end() ? State.createEnd(representative.name()) : State.create(representative.name());
            if (group.contains(initial)) {
                minInitial = minState;
            }

            Pair<State, State> p = Pair.of(representative, minState);
            minStates.add(p);
            group.forEach(s -> minStatesByStates.put(s, p));
        }

        minStates.forEach(p -> {
            State representative = p.a();
            State minState = p.b();

            for (TargetedRule<State> rule : representative.rules()) {
                minState.addRule(minStatesByStates.get(rule.target()).b(), rule.rule());
            }
        });
        return new DFA(minInitial);
    }
}
