package io.github.ititus.commons.automaton.finite.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;

import static io.github.ititus.commons.automaton.finite.rule.Rule.*;

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
                return none();
            } else if (rules.size() == 1) {
                return rules.get(0);
            }

            return new Or(List.copyOf(rules));
        } else {
            if (notRules.isEmpty()) {
                return all();
            } else if (notRules.size() == 1) {
                return new Not(notRules.get(0));
            }

            return new Not(new Or(List.copyOf(notRules)));
        }
    }

    /**
     * codepoints must be sorted and distinct.
     */
    static Rule simplify(int[] codepoints) {
        if (codepoints.length == 0) {
            return none();
        } else if (codepoints.length == 1) {
            return new Single(codepoints[0]);
        }

        boolean not = codepoints.length > (Rule.CODE_POINT_COUNT / 2);
        List<Rule> rules = new ArrayList<>();
        if (not) {
            if (codepoints[0] > MIN_CODE_POINT) {
                rules.add(range(MIN_CODE_POINT, codepoints[0] - 1));
            }

            for (int start = 0, len = codepoints.length - 1; start < len; start++) {
                while (start < len && codepoints[start] == codepoints[start + 1] - 1) {
                    start++;
                }

                if (start < len) {
                    rules.add(range(codepoints[start] + 1, codepoints[start + 1] - 1));
                }
            }

            if (codepoints[codepoints.length - 1] < MAX_CODE_POINT) {
                rules.add(range(codepoints[codepoints.length - 1] + 1, MAX_CODE_POINT));
            }

            if (rules.isEmpty()) {
                return all();
            } else if (rules.size() == 1) {
                return new Not(rules.get(0));
            }

            return new Not(new Or(List.copyOf(rules)));
        } else {
            for (int start = 0, len = codepoints.length; start < len; ) {
                int end = start + 1;
                while (end < len && codepoints[end] == codepoints[end - 1] + 1) {
                    end++;
                }

                rules.add(range(codepoints[start], codepoints[end - 1]));
                start = end;
            }

            if (rules.isEmpty()) {
                return none();
            } else if (rules.size() == 1) {
                return rules.get(0);
            }

            return new Or(List.copyOf(rules));
        }
    }
}
