package io.github.ititus.commons.automaton.finite.rule;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class Or implements Rule {

    private final List<Rule> rules;

    Or(List<Rule> rules) {
        super();
        this.rules = rules;
    }

    List<Rule> rules() {
        return rules;
    }

    @Override
    public String describe() {
        return "[ " + rules().stream()
                .map(Rule::describe)
                .collect(Collectors.joining(" "))
                + " ]";
    }

    @Override
    public boolean accepts(int codepoint) {
        return rules().stream()
                .anyMatch(r -> r.accepts(codepoint));
    }

    @Override
    public IntStream validCodepoints() {
        return rules.stream().flatMapToInt(Rule::validCodepoints);
    }

    @Override
    public int validCodepointCount() {
        return rules.stream().mapToInt(Rule::validCodepointCount).sum();
    }
}
