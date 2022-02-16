package io.github.ititus.commons.automaton.finite;

import java.util.function.Function;

public final class Rules {

    private Rules() {
    }

    public static <T extends BaseState<T>> Function<T, Rule<T>> binDigit() {
        return target -> new Rule.Range<>(target, '0', '1');
    }

    public static <T extends BaseState<T>> Function<T, Rule<T>> octDigit() {
        return target -> new Rule.Range<>(target, '0', '7');
    }

    public static <T extends BaseState<T>> Function<T, Rule<T>> decDigit() {
        return target -> new Rule.Range<>(target, '0', '9');
    }

    public static <T extends BaseState<T>> Function<T, Rule<T>> decDigitWithoutZero() {
        return target -> new Rule.Range<>(target, '1', '9');
    }

    public static <T extends BaseState<T>> Function<T, Rule<T>> hexDigit() {
        return target -> new Rule.Range<>(target, '0', '9')
                .merge(new Rule.Range<>(target, 'a', 'f'))
                .merge(new Rule.Range<>(target, 'A', 'F'));
    }
}
