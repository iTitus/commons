package io.github.ititus.commons.automaton.finite.rule;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

record Or(List<Rule> rules) implements Rule {

    @Override
    public String describe() {
        return "[ " + rules.stream()
                .map(Rule::describe)
                .collect(Collectors.joining(" "))
                + " ]";
    }

    @Override
    public boolean accepts(char c) {
        for (Rule r : rules) {
            if (r.accepts(c)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public IntStream validChars() {
        return rules.stream().flatMapToInt(Rule::validChars);
    }

    @Override
    public int validCharCount() {
        return rules.stream().mapToInt(Rule::validCharCount).sum();
    }
}
