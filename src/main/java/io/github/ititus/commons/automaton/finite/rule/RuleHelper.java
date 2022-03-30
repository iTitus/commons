package io.github.ititus.commons.automaton.finite.rule;

import io.github.ititus.commons.function.CharPredicate;

import java.util.ArrayList;
import java.util.List;

import static io.github.ititus.commons.automaton.finite.rule.Rule.*;

final class RuleHelper {

    private RuleHelper() {}

    static Rule simplify(CharPredicate acceptor) {
        List<Rule> rules = new ArrayList<>();
        List<Rule> notRules = new ArrayList<>();

        char lastStart = MIN_VALUE;
        boolean lastAccept = acceptor.test(lastStart);
        for (char c = MIN_VALUE + 1; ; c++) {
            boolean accept = acceptor.test(c);
            if (accept != lastAccept) {
                (lastAccept ? rules : notRules).add(range(lastStart, (char) (c - 1)));
                lastStart = c;
                lastAccept = accept;
            }

            if (c == MAX_VALUE) {
                break;
            }
        }

        (lastAccept ? rules : notRules).add(range(lastStart, MAX_VALUE));

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
     * characters must be sorted and distinct.
     */
    static Rule simplify(char[] characters) {
        if (characters.length == 0) {
            return none();
        } else if (characters.length == 1) {
            return new Single(characters[0]);
        }

        boolean not = characters.length > (CHAR_COUNT / 2);
        List<Rule> rules = new ArrayList<>();
        if (not) {
            if (characters[0] > MIN_VALUE) {
                rules.add(range(MIN_VALUE, (char) (characters[0] - 1)));
            }

            for (int start = 0, len = characters.length - 1; start < len; start++) {
                while (start < len && characters[start] == characters[start + 1] - 1) {
                    start++;
                }

                if (start < len) {
                    rules.add(range((char) (characters[start] + 1), (char) (characters[start + 1] - 1)));
                }
            }

            if (characters[characters.length - 1] < MAX_VALUE) {
                rules.add(range((char) (characters[characters.length - 1] + 1), MAX_VALUE));
            }

            if (rules.isEmpty()) {
                return all();
            } else if (rules.size() == 1) {
                return new Not(rules.get(0));
            }

            return new Not(new Or(List.copyOf(rules)));
        } else {
            for (int start = 0, len = characters.length; start < len; ) {
                int end = start + 1;
                while (end < len && characters[end] == characters[end - 1] + 1) {
                    end++;
                }

                rules.add(range(characters[start], characters[end - 1]));
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
