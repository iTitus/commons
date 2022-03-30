package io.github.ititus.commons.automaton.finite.rule;

import java.util.stream.Collectors;

final class Not extends CachedRule {

    private final Rule rule;

    Not(Rule rule) {
        super();
        this.rule = rule;
    }

    Rule rule() {
        return rule;
    }

    @Override
    public String describe() {
        if (rule instanceof Or or) {
            return "[^ " + or.rules().stream()
                    .map(Rule::describe)
                    .collect(Collectors.joining(" "))
                    + " ]";
        }

        return "[^ " +
                rule.describe()
                + " ]";
    }

    @Override
    public boolean accepts(char c) {
        return !rule.accepts(c);
    }

    @Override
    public int validCharCount() {
        return Rule.CHAR_COUNT - rule.validCharCount();
    }
}
