package io.github.ititus.commons.automaton.finite.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;

final class RuleHelper {

    private RuleHelper() {}

    static Rule simplify(IntPredicate acceptor) {
        List<Rule> rules = new ArrayList<>();
        List<Rule> notRules = new ArrayList<>();

        int lastStart = Rule.MIN_CODE_POINT;
        boolean lastAccept = acceptor.test(lastStart);
        for (int cp = Rule.MIN_CODE_POINT + 1; cp <= Rule.MAX_CODE_POINT; cp++) {
            boolean accept = acceptor.test(cp);
            if (accept != lastAccept) {
                (lastAccept ? rules : notRules).add(Rule.range(lastStart, cp - 1));
                lastStart = cp;
                lastAccept = accept;
            }
        }

        (lastAccept ? rules : notRules).add(Rule.range(lastStart, Rule.MAX_CODE_POINT));

        if (rules.size() <= notRules.size()) {
            if (rules.isEmpty()) {
                return Rule.none();
            } else if (rules.size() == 1) {
                return rules.get(0);
            }

            return new Or(List.copyOf(rules));
        } else {
            if (notRules.isEmpty()) {
                return Rule.all();
            } else if (notRules.size() == 1) {
                return new Not(notRules.get(0));
            }

            return new Not(new Or(List.copyOf(notRules)));
        }
    }
}
