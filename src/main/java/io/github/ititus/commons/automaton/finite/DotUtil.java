package io.github.ititus.commons.automaton.finite;

import io.github.ititus.commons.automaton.finite.dfa.DFA;
import io.github.ititus.commons.automaton.finite.nfa.NFA;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@SuppressWarnings("Duplicates")
public final class DotUtil {

    private DotUtil() {
    }

    public static String toDot(DFA dfa) {
        StringBuilder b = new StringBuilder();
        b.append("digraph G {\n");

        Set<io.github.ititus.commons.automaton.finite.dfa.State> visited = new HashSet<>();

        Deque<io.github.ititus.commons.automaton.finite.dfa.State> q = new LinkedList<>();
        if (dfa.initial() != null) {
            q.addLast(dfa.initial());
        }

        while (!q.isEmpty()) {
            var state = q.removeFirst();
            if (!visited.add(state)) {
                continue;
            }

            defineState(b, state, dfa.initial() == state);

            for (var rule : state.rules()) {
                defineRule(b, state, rule);
                q.addLast(rule.target());
            }
        }

        b.append("}\n");
        return b.toString();
    }

    public static String toDot(NFA nfa) {
        StringBuilder b = new StringBuilder();
        b.append("digraph G {\n");

        Set<io.github.ititus.commons.automaton.finite.nfa.State> visited = new HashSet<>();

        Deque<io.github.ititus.commons.automaton.finite.nfa.State> q = new LinkedList<>();
        if (nfa.initial() != null) {
            q.addLast(nfa.initial());
        }

        while (!q.isEmpty()) {
            var state = q.removeFirst();
            if (!visited.add(state)) {
                continue;
            }

            defineState(b, state, nfa.initial() == state);

            for (var rule : state.rules()) {
                defineRule(b, state, rule);
                q.addLast(rule.target());
            }

            for (var s : state.epsilonTransitions()) {
                defineEpsilon(b, state, s);
                q.addLast(s);
            }
        }

        b.append("}\n");
        return b.toString();
    }

    private static void defineState(StringBuilder b, BaseState<?> state, boolean isInitial) {
        b.append("    ").append(quoteDot(state.name()));
        b.append(" [");
        b.append("label=").append(quoteDot(state.name()));
        if (state.end()) {
            b.append(" shape=doublecircle");
        }
        b.append("];\n");

        if (isInitial) {
            b.append("    ").append("\"__start__\" [style=invisible];\n");
            b.append("    ").append("\"__start__\" -> ").append(quoteDot(state.name())).append(";\n");
        }
    }

    private static void defineRule(StringBuilder b, BaseState<?> state, TargetedRule<?> rule) {
        b.append("    ").append(quoteDot(state.name()));
        b.append(" -> ");
        b.append(quoteDot(rule.target().name()));
        b.append(" [");
        b.append("label=").append(quoteDot(rule.describe()));
        b.append("];\n");
    }

    private static void defineEpsilon(StringBuilder b, BaseState<?> state, BaseState<?> target) {
        b.append("    ").append(quoteDot(state.name()));
        b.append(" -> ");
        b.append(quoteDot(target.name()));
        b.append(" [");
        b.append("label=ε");
        b.append("];\n");
    }

    private static String quoteDot(String s) {
        StringBuilder b = new StringBuilder().append('"');
        for (int i = 0, length = s.length(); i < length; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"' -> b.append('\\').append('"');
                case '\\' -> b.append('\\').append('\\');
                default -> b.append(c);
            }
        }

        return b.append('"').toString();
    }

    public static String toStringAsEscape(char c) {
        return switch (c) {
            case '\b' -> "\\b";
            case ' ' -> "\\s";
            case '\t' -> "\\t";
            case '\n' -> "\\n";
            case '\f' -> "\\f";
            case '\r' -> "\\r";
            case '\u000b' -> "\\v";
            default -> Character.toString(c);
        };
    }
}
