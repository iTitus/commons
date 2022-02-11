package io.github.ititus.commons.parser;

import io.github.ititus.commons.data.either.Either;
import io.github.ititus.commons.data.pair.Pair;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface Parser<I, R> {

    Either<ParserError, ParserResult<I, R>> parse(Input<I> in);

    default <S> Parser<I, S> bind(Function<? super R, ? extends Parser<I, S>> f) {
        return Parsers.bind(this, f);
    }

    default <S> Parser<I, S> bindAlways(Function<? super R, ? extends S> f) {
        return Parsers.bindAlways(this, f);
    }

    default Parser<I, R> end() {
        return Parsers.end(this);
    }

    default <S> Parser<I, S> then(Parser<I, ? extends S> p) {
        return Parsers.then(this, p);
    }

    default Parser<I, R> thenSkip(Parser<I, ?> p) {
        return Parsers.thenSkip(this, p);
    }

    default <S> Parser<I, Pair<R, S>> pairThen(Parser<I, ? extends S> p) {
        return Parsers.pairThen(this, p);
    }

    default Parser<I, R> or(Parser<I, ? extends R> p) {
        return Parsers.or(this, p);
    }

    default <S> Parser<I, Either<R, S>> eitherOr(Parser<I, ? extends S> p) {
        return Parsers.eitherOr(this, p);
    }

    default Parser<I, Optional<R>> optional() {
        return Parsers.optional(this);
    }

    default Parser<I, List<R>> zeroOrMoreList() {
        return Parsers.zeroOrMoreList(this);
    }

    default Parser<I, List<R>> zeroOrMoreList(Parser<I, ?> separator) {
        return Parsers.zeroOrMoreList(this, separator);
    }

    default <S> Parser<I, S> zeroOrMore(Supplier<? extends S> identity, BiFunction<? super S, ? super R, ? extends S> acc) {
        return Parsers.zeroOrMore(this, identity, acc);
    }

    default <S> Parser<I, S> zeroOrMore(Parser<I, ?> separator, Supplier<? extends S> identity, BiFunction<? super S, ? super R, ? extends S> acc) {
        return Parsers.zeroOrMore(this, separator, identity, acc);
    }

    default Parser<I, List<R>> oneOrMoreList() {
        return Parsers.oneOrMoreList(this);
    }

    default Parser<I, List<R>> oneOrMoreList(Parser<I, ?> separator) {
        return Parsers.oneOrMoreList(this, separator);
    }

    default <S> Parser<I, S> oneOrMore(Supplier<? extends S> identity, BiFunction<? super S, ? super R, ? extends S> acc) {
        return Parsers.oneOrMore(this, identity, acc);
    }

    default <S> Parser<I, S> oneOrMore(Parser<I, ?> separator, Supplier<? extends S> identity, BiFunction<? super S, ? super R, ? extends S> acc) {
        return Parsers.oneOrMore(this, separator, identity, acc);
    }

    default Parser<I, Eps> skip() {
        return Parsers.skip(this);
    }

    default <S> Parser<I, S> map(Function<? super R, ? extends S> f) {
        return Parsers.map(this, f);
    }

    default Optional<ParserResult<I, R>> tryParse(Input<I> in) {
        return parse(in).right();
    }

    default ParserResult<I, R> doParse(Input<I> in) {
        var result = parse(in);
        return result.right().orElseThrow(result::tryGetLeft);
    }
}
