package io.github.ititus.commons.automaton.finite.rule;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public sealed interface Rule permits All, CachedRule, None, Or, Range, Single {

    int MIN_CODE_POINT = Character.MIN_CODE_POINT;
    int MAX_CODE_POINT = Character.MAX_CODE_POINT;
    int CODE_POINT_COUNT = MAX_CODE_POINT - MIN_CODE_POINT + 1;

    static Rule none() {
        return None.INSTANCE;
    }

    static Rule all() {
        return All.INSTANCE;
    }

    static Rule codepoint(int codepoint) {
        if (codepoint < MIN_CODE_POINT || codepoint > MAX_CODE_POINT) {
            throw new IllegalArgumentException("codepoint out of bounds");
        }

        return new Single(codepoint);
    }

    static Rule codepoints(int... codepoints) {
        if (codepoints == null || codepoints.length == 0) {
            return none();
        } else if (codepoints.length == 1) {
            return codepoint(codepoints[0]);
        }

        return codepoints(Arrays.stream(codepoints));
    }

    static Rule codepoints(IntStream codepoints) {
        if (codepoints == null) {
            return none();
        }

        return or(codepoints.mapToObj(Rule::codepoint));
    }

    static Rule range(int start, int end) {
        if (start < MIN_CODE_POINT || start > MAX_CODE_POINT) {
            throw new IllegalArgumentException("start out of bounds");
        } else if (end < MIN_CODE_POINT || end > MAX_CODE_POINT) {
            throw new IllegalArgumentException("end out of bounds");
        } else if (start > end) {
            throw new IllegalArgumentException("start > end");
        } else if (start == end) {
            return codepoint(start);
        }

        return new Range(start, end);
    }

    static Rule or(Rule... rules) {
        if (rules == null || rules.length == 0) {
            return none();
        } else if (rules.length == 1) {
            return rules[0];
        }

        return or(Arrays.stream(rules));
    }

    static Rule or(Collection<Rule> rules) {
        int size;
        if (rules == null || (size = rules.size()) == 0) {
            return none();
        } else if (size == 1) {
            return rules.iterator().next();
        }

        return or(rules.stream());
    }

    static Rule or(Stream<Rule> rules) {
        if (rules == null) {
            return none();
        }

        List<Rule> ruleList = rules.flatMap(r -> r == null ? Stream.of() : r instanceof Or or ? or.rules().stream() : Stream.of(r))
                .filter(r -> !(r instanceof None))
                .toList();
        int size = ruleList.size();
        if (size == 0) {
            return none();
        } else if (size == 1) {
            return ruleList.get(0);
        } else if (ruleList.stream().anyMatch(r -> r instanceof All)) {
            return all();
        }

        return RuleHelper.simplify(cp -> ruleList.stream().anyMatch(r -> r.accepts(cp)));
    }

    static Rule not(Rule rule) {
        Objects.requireNonNull(rule);
        if (rule instanceof None) {
            return all();
        } else if (rule instanceof All) {
            return none();
        } else if (rule instanceof Not not) {
            return not.rule();
        }

        return new Not(rule);
    }

    static Rule custom(IntPredicate codepointPredicate) {
        return RuleHelper.simplify(codepointPredicate);
    }

    String describe();

    boolean accepts(int codepoint);

    default Rule merge(Rule other) {
        return or(this, other);
    }

    IntStream validCodepoints();

    int validCodepointCount();

}
