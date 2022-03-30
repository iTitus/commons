package io.github.ititus.commons.automaton.finite.rule;

import io.github.ititus.commons.data.ArrayUtil;
import io.github.ititus.commons.data.StreamUtil;
import io.github.ititus.commons.function.CharPredicate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public sealed interface Rule permits All, CachedRule, None, Or, Range, Single {

    char MIN_VALUE = Character.MIN_VALUE;
    char MAX_VALUE = Character.MAX_VALUE;
    int CHAR_COUNT = MAX_VALUE - MIN_VALUE + 1;

    static Rule none() {
        return None.INSTANCE;
    }

    static Rule all() {
        return All.INSTANCE;
    }

    static Rule character(char c) {
        return new Single(c);
    }

    static Rule characters(char... cs) {
        if (cs == null || cs.length == 0) {
            return none();
        } else if (cs.length == 1) {
            return character(cs[0]);
        }

        return characters(ArrayUtil.stream(cs));
    }

    static Rule characters(IntStream characters) {
        if (characters == null) {
            return none();
        }

        return RuleHelper.simplify(StreamUtil.toCharArray(
                characters
                        .peek(c -> {
                            if (c < MIN_VALUE || c > MAX_VALUE) {
                                throw new IllegalArgumentException("character out of bounds");
                            }
                        })
                        .sorted()
                        .distinct()
        ));
    }

    static Rule range(char start, char end) {
        if (start > end) {
            throw new IllegalArgumentException("start > end");
        } else if (start == end) {
            return character(start);
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

        return RuleHelper.simplify(c -> ruleList.stream().anyMatch(r -> r.accepts(c)));
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

    static Rule custom(CharPredicate charPredicate) {
        return RuleHelper.simplify(charPredicate);
    }

    String describe();

    boolean accepts(char c);

    default Rule merge(Rule other) {
        return or(this, other);
    }

    IntStream validChars();

    int validCharCount();

}
