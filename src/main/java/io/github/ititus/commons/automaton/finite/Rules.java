package io.github.ititus.commons.automaton.finite;

import io.github.ititus.commons.automaton.finite.rule.Rule;

public final class Rules {

    public static final Rule BIN_DIGIT = Rule.range('0', '1');
    public static final Rule BIN_DIGIT_WITHOUT_ZERO = Rule.codepoint('1');
    public static final Rule OCT_DIGIT = Rule.range('0', '7');
    public static final Rule OCT_DIGIT_WITHOUT_ZERO = Rule.range('1', '7');
    public static final Rule DEC_DIGIT = Rule.range('0', '9');
    public static final Rule DEC_DIGIT_WITHOUT_ZERO = Rule.range('1', '9');
    public static final Rule HEX_DIGIT = Rule.or(Rule.range('0', '9'), Rule.range('a', 'f'), Rule.range('A', 'F'));
    public static final Rule HEX_DIGIT_WITHOUT_ZERO = Rule.or(Rule.range('1', '9'), Rule.range('a', 'f'), Rule.range('A', 'F'));

    private Rules() {
    }
}
