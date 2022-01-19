package io.github.ititus.commons.parser;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Parsers {

    public static <IN, OUT> Parser<IN, OUT> empty(Supplier<OUT> result) {
        return in -> Optional.of(ParserResult.of(in, result.get()));
    }

    public static <IN, OUT> Parser<IN, OUT> noAccept() {
        return in -> Optional.empty();
    }

    public static <IN, P_OUT, ACC, OUT> Parser<IN, OUT> many(Supplier<ACC> emptyAccumulator,
                                                             Supplier<OUT> emptyResult,
                                                             BiFunction<ACC, P_OUT, ACC> combinator, Function<ACC,
            OUT> finalizer, Parser<IN, P_OUT> parser) {
        return in -> {
            boolean empty = true;
            ACC current = emptyAccumulator.get();

            while (true) {
                Optional<ParserResult<IN, P_OUT>> parserResultOptional = parser.parse(in);
                if (parserResultOptional.isEmpty()) {
                    break;
                }

                ParserResult<IN, P_OUT> parserResult = parserResultOptional.get();

                in = parserResult.getRemaining();
                P_OUT result = parserResult.getResult();
                current = combinator.apply(current, result);
                empty = false;
            }

            if (empty) {
                return Optional.of(ParserResult.of(in, emptyResult.get()));
            }
            return Optional.of(ParserResult.of(in, finalizer.apply(current)));
        };
    }


    public static <IN, P_OUT, ACC, OUT> Parser<IN, OUT> some(Supplier<ACC> emptyAccumulator, BiFunction<ACC, P_OUT,
            ACC> combinator, Function<ACC, OUT> finalizer, Parser<IN, P_OUT> parser) {
        return in -> {
            boolean empty = true;
            ACC current = emptyAccumulator.get();

            while (true) {
                Optional<ParserResult<IN, P_OUT>> parserResultOptional = parser.parse(in);
                if (parserResultOptional.isEmpty()) {
                    break;
                }

                ParserResult<IN, P_OUT> parserResult = parserResultOptional.get();

                in = parserResult.getRemaining();
                P_OUT result = parserResult.getResult();
                current = combinator.apply(current, result);
                empty = false;
            }

            if (empty) {
                return Optional.empty();
            }
            return Optional.of(ParserResult.of(in, finalizer.apply(current)));
        };
    }

    @SafeVarargs
    public static <IN, P_OUT, ACC, OUT> Parser<IN, OUT> sequence(Supplier<ACC> emptyAccumulator, BiFunction<ACC,
            P_OUT, ACC> combinator, Function<ACC, OUT> finalizer, Parser<IN, P_OUT>... parsers) {
        return in -> {
            ACC current = emptyAccumulator.get();

            for (Parser<IN, P_OUT> parser : parsers) {
                Optional<ParserResult<IN, P_OUT>> parserResultOptional = parser.parse(in);
                if (parserResultOptional.isEmpty()) {
                    return Optional.empty();
                }

                ParserResult<IN, P_OUT> parserResult = parserResultOptional.get();

                in = parserResult.getRemaining();
                P_OUT result = parserResult.getResult();
                current = combinator.apply(current, result);
            }

            return Optional.of(ParserResult.of(in, finalizer.apply(current)));
        };
    }

    @SafeVarargs
    public static <IN, OUT> Parser<IN, OUT> or(Parser<IN, OUT>... parsers) {
        if (parsers.length == 0) {
            throw new IllegalArgumentException();
        }

        return in -> {
            for (Parser<IN, OUT> parser : parsers) {
                Optional<ParserResult<IN, OUT>> parserResultOptional = parser.parse(in);
                if (parserResultOptional.isPresent()) {
                    return parserResultOptional;
                }
            }

            return Optional.empty();
        };
    }

    public static Parser<String, Character> character(Predicate<Character> characterPredicate) {
        return in -> {
            if (!in.isEmpty()) {
                char actual = in.charAt(0);
                if (characterPredicate.test(actual)) {
                    return Optional.of(ParserResult.of(in.substring(1), actual));
                }
            }
            return Optional.empty();
        };
    }

    public static Parser<String, Character> digit() {
        return character(c -> '0' <= c && c <= '9');
    }

    public static Parser<String, Integer> natural() {
        return some(StringBuilder::new, StringBuilder::append, b -> Integer.parseInt(b.toString()), digit());
    }

    public static Parser<String, Integer> integer() {
        Parser<String, Integer> natural = natural();
        Parser<String, Character> minus = character(c -> c == '-');
        return in -> {
            Optional<ParserResult<String, Character>> minusRes = minus.parse(in);
            if (minusRes.isEmpty()) {
                return natural.parse(in);
            }

            Optional<ParserResult<String, Integer>> naturalRes = natural.parse(minusRes.get().getRemaining());
            if (naturalRes.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(ParserResult.of(naturalRes.get().getRemaining(), -naturalRes.get().getResult()));
        };
    }
}
