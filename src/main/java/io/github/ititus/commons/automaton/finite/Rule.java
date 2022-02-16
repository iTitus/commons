package io.github.ititus.commons.automaton.finite;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface Rule<T extends BaseState<T>> {

    T target();

    String describe();

    boolean accepts(int codepoint);

    default Rule<T> merge(Rule<T> other) {
        return Or.create(target(), this, other);
    }

    IntStream validCodepoints();

    abstract class AbstractRule<T extends BaseState<T>> implements Rule<T> {

        private final T target;
        private int[] validCodepointCache;

        protected AbstractRule(T target) {
            this.target = target;
            this.validCodepointCache = null;
        }

        @Override
        public T target() {
            return target;
        }

        @Override
        public IntStream validCodepoints() {
            if (validCodepointCache == null) {
                validCodepointCache = _validCodepoints().sorted().distinct().toArray();
            }

            return Arrays.stream(validCodepointCache);
        }

        protected IntStream _validCodepoints() {
            return IntStream.rangeClosed(Character.MIN_CODE_POINT, Character.MAX_CODE_POINT).filter(this::accepts);
        }
    }

    abstract class CompositeRule<T extends BaseState<T>> extends AbstractRule<T> {

        private final List<Rule<T>> rules;

        protected CompositeRule(T target, List<Rule<T>> rules) {
            super(target);
            this.rules = rules;
        }

        protected static <T extends BaseState<T>> List<Rule<T>> flatten(Stream<Rule<T>> rules) {
            return rules
                    .flatMap(r -> {
                        if (r instanceof Or) {
                            return ((Or<T>) r).rules().stream();
                        }

                        return Stream.of(r);
                    })
                    .peek(r -> {
                        if (r instanceof Not || r instanceof Or) {
                            throw new IllegalArgumentException("nested NOT/OR is not allowed");
                        }
                    })
                    .toList();
        }

        protected List<Rule<T>> rules() {
            return rules;
        }
    }

    class Simple<T extends BaseState<T>> extends AbstractRule<T> {

        private final int codepoint;

        public Simple(T target, int codepoint) {
            super(target);
            this.codepoint = codepoint;
        }

        @Override
        public String describe() {
            return DotUtil.toStringAsEscape(codepoint);
        }

        @Override
        public boolean accepts(int codepoint) {
            return codepoint == this.codepoint;
        }

        @Override
        public Rule<T> merge(Rule<T> other) {
            if (other instanceof Simple<T> simple) {
                if (codepoint == simple.codepoint) {
                    return this;
                } else if (Math.abs(codepoint - simple.codepoint) == 1) {
                    return new Range<>(target(), Math.min(codepoint, simple.codepoint), Math.max(codepoint, simple.codepoint));
                }
            } else if (other instanceof Range<T> range) {
                if (range.start == range.end && range.start == codepoint) {
                    return this;
                } else if (range.accepts(codepoint)) {
                    return range;
                } else if (codepoint == range.start - 1) {
                    return new Range<>(target(), codepoint, range.end);
                } else if (codepoint == range.end + 1) {
                    return new Range<>(target(), range.start, codepoint);
                }
            }

            return super.merge(other);
        }

        @Override
        public IntStream validCodepoints() {
            return IntStream.of(codepoint);
        }
    }

    class Range<T extends BaseState<T>> extends AbstractRule<T> {

        private final int start;
        private final int end;

        public Range(T target, int start, int end) {
            super(target);
            if (start > end) {
                throw new IllegalArgumentException();
            }

            this.start = start;
            this.end = end;
        }

        @Override
        public String describe() {
            return DotUtil.toStringAsEscape(start) + "-" + DotUtil.toStringAsEscape(end);
        }

        @Override
        public boolean accepts(int codepoint) {
            return start <= codepoint && codepoint <= end;
        }

        @Override
        public Rule<T> merge(Rule<T> other) {
            if (other instanceof Simple<T> simple) {
                if (start == end && start == simple.codepoint) {
                    return simple;
                } else if (accepts(simple.codepoint)) {
                    return this;
                } else if (simple.codepoint == start - 1) {
                    return new Range<>(target(), simple.codepoint, end);
                } else if (simple.codepoint == end + 1) {
                    return new Range<>(target(), start, simple.codepoint);
                }
            } else if (other instanceof Range<T> range) {
                if (start == range.start && end == range.end) {
                    if (start == end) {
                        return new Simple<>(target(), start);
                    }

                    return this;
                } else if (accepts(range.start) && accepts(range.end)) {
                    return this;
                } else if (range.accepts(start) && range.accepts(end)) {
                    return range;
                } else if (Math.min(end, range.end) + 1 >= Math.max(start, range.start)) {
                    return new Range<>(target(), Math.min(start, range.start), Math.max(end, range.end));
                }
            }

            return super.merge(other);
        }

        @Override
        public IntStream validCodepoints() {
            return IntStream.rangeClosed(start, end);
        }
    }

    class Not<T extends BaseState<T>> extends CompositeRule<T> {

        private Not(T target, List<Rule<T>> rules) {
            super(target, rules);
        }

        @SafeVarargs
        public static <T extends BaseState<T>> Rule<T> create(T target, Rule<T>... rules) {
            return create(target, Arrays.stream(rules));
        }

        public static <T extends BaseState<T>> Rule<T> create(T target, List<Rule<T>> rules) {
            return create(target, rules.stream());
        }

        public static <T extends BaseState<T>> Rule<T> create(T target, Stream<Rule<T>> rules) {
            List<Rule<T>> ruleList = flatten(rules);
            if (ruleList.isEmpty()) {
                throw new IllegalArgumentException();
            }

            return new Not<>(target, ruleList);
        }

        @Override
        public String describe() {
            return "[^ " +
                    rules().stream()
                            .map(Rule::describe)
                            .collect(Collectors.joining(" "))
                    + " ]";
        }

        @Override
        public boolean accepts(int codepoint) {
            return rules().stream()
                    .noneMatch(r -> r.accepts(codepoint));
        }

        @Override
        public Rule<T> merge(Rule<T> other) {
            return create(target(), Stream.concat(
                    rules().stream(),
                    other instanceof Not ? ((Not<T>) other).rules().stream() : Stream.of(other)
            ));
        }
    }

    class Or<T extends BaseState<T>> extends CompositeRule<T> {

        private Or(T target, List<Rule<T>> rules) {
            super(target, rules);
        }

        @SafeVarargs
        public static <T extends BaseState<T>> Rule<T> create(T target, Rule<T>... rules) {
            return create(target, Arrays.stream(rules));
        }

        public static <T extends BaseState<T>> Rule<T> create(T target, List<Rule<T>> rules) {
            return create(target, rules.stream());
        }

        public static <T extends BaseState<T>> Rule<T> create(T target, Stream<Rule<T>> rules) {
            List<Rule<T>> ruleList = flatten(rules);
            if (ruleList.size() <= 1) {
                throw new IllegalArgumentException();
            }

            return new Or<>(target, ruleList);
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
        public Rule<T> merge(Rule<T> other) {
            return create(target(), Stream.concat(rules().stream(), Stream.of(other)));
        }

        @Override
        protected IntStream _validCodepoints() {
            return rules().stream().flatMapToInt(Rule::validCodepoints);
        }
    }

    class Custom<T extends BaseState<T>> extends AbstractRule<T> {

        private final String description;
        private final IntPredicate codepointPredicate;

        public Custom(T target, String description, IntPredicate codepointPredicate) {
            super(target);
            this.description = description;
            this.codepointPredicate = codepointPredicate;
        }

        @Override
        public String describe() {
            return description;
        }

        @Override
        public boolean accepts(int codepoint) {
            return codepointPredicate.test(codepoint);
        }
    }
}
