package io.github.ititus.commons.automaton.finite;

import io.github.ititus.commons.automaton.finite.rule.Rule;

import java.util.Objects;
import java.util.stream.IntStream;

public record TargetedRule<S extends BaseState<S>>(S target, Rule rule) {

    public TargetedRule {
        Objects.requireNonNull(target);
        Objects.requireNonNull(rule);
    }

    public TargetedRule<S> merge(TargetedRule<S> other) {
        if (!target.equals(other.target)) {
            throw new IllegalArgumentException("different targets");
        }

        Rule merged = rule.merge(other.rule);
        if (merged == rule) {
            return this;
        } else if (merged == other.rule) {
            return other;
        }

        return new TargetedRule<>(target, merged);
    }

    public String describe() {
        return rule.describe();
    }

    public boolean accepts(char c) {
        return rule.accepts(c);
    }

    public IntStream validChars() {
        return rule.validChars();
    }
}
