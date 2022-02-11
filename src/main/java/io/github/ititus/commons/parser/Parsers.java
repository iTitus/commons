package io.github.ititus.commons.parser;

import io.github.ititus.commons.data.either.Either;
import io.github.ititus.commons.data.pair.Pair;
import io.github.ititus.commons.function.CharPredicate;
import io.github.ititus.commons.math.number.BigIntegerConstants;
import io.github.ititus.commons.math.number.BigIntegerMath;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.ititus.commons.parser.Eps.EPS;
import static java.util.function.Function.identity;

public final class Parsers {

    private Parsers() {}

    public static <I> Parser<I, Eps> end() {
        return in -> in.isEmpty() ? Either.right(ParserResult.of(in, EPS)) : Either.left(new ParserError("expected eof, but got '" + in.get() + "'"));
    }

    public static <I, R> Parser<I, R> end(Parser<I, ? extends R> p) {
        return thenSkip(p, end());
    }

    public static <I> Parser<I, Eps> eps() {
        return always(EPS);
    }

    public static <I> Parser<I, Eps> empty() {
        return end(eps());
    }

    public static <I, R> Parser<I, R> always(R result) {
        return in -> Either.right(ParserResult.of(in, result));
    }

    public static <I, R> Parser<I, R> always(Supplier<? extends R> result) {
        Objects.requireNonNull(result);
        return in -> Either.right(ParserResult.of(in, result.get()));
    }

    public static <I, R> Parser<I, R> never() {
        return never(() -> new ParserError("never accept"));
    }

    public static <I, R> Parser<I, R> never(ParserError e) {
        Objects.requireNonNull(e);
        return in -> Either.left(e);
    }

    public static <I, R> Parser<I, R> never(Supplier<? extends ParserError> e) {
        Objects.requireNonNull(e);
        return in -> Either.left(e.get());
    }

    public static <I, R, S> Parser<I, S> bind(Parser<I, ? extends R> p, Function<? super R, ? extends Parser<I, S>> f) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(f);
        return in -> p.parse(in).map(Either::left, r -> f.apply(r.result()).parse(r.remaining()));
    }

    public static <I, R, S> Parser<I, S> bindAlways(Parser<I, ? extends R> p, Function<? super R, ? extends S> f) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(f);
        return in -> p.parse(in).mapBoth(identity(), r -> r.map(f));
    }

    public static <I, R, S> Parser<I, S> map(Parser<I, ? extends R> p, Function<? super R, ? extends S> f) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(f);
        return in -> p.parse(in).mapRight(r -> r.map(f));
    }

    public static <I, R, S> Parser<I, S> reduce(Parser<I, ? extends Iterable<R>> p, S identity, BiFunction<? super S, ? super R, ? extends S> acc) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(identity);
        Objects.requireNonNull(acc);
        return bindAlways(p, rs -> {
            S s = identity;
            for (R r : rs) {
                s = acc.apply(s, r);
            }

            return s;
        });
    }

    public static Parser<Character, Character> characterLookahead() {
        return in -> in.isEmpty() ? Either.left(new ParserError("unexpected eof")) : Either.right(ParserResult.of(in, in.get()));
    }

    public static Parser<Character, Character> characterLookahead(char c) {
        return characterLookahead(c_ -> c_ == c);
    }

    public static Parser<Character, Character> characterLookahead(CharPredicate c) {
        Objects.requireNonNull(c);
        return in -> characterLookahead().parse(in).flatMapRight(r -> c.test(r.result()) ? Either.right(r) : Either.left(new ParserError("unexpected char '" + r.result() + "'")));
    }

    public static Parser<Character, String> stringLookahead(int n) {
        return in -> {
            if (!(in instanceof CharInput cin)) {
                throw new IllegalArgumentException("string lookahead is only supported for CharInput");
            }

            CharSequence s = cin.seq();
            return s.length() < n ? Either.left(new ParserError("unexpected eof")) : Either.right(ParserResult.of(in, s.subSequence(0, n).toString()));
        };
    }

    public static Parser<Character, String> stringLookahead(String s) {
        Objects.requireNonNull(s);
        return in -> stringLookahead(s.length()).parse(in).flatMapRight(r -> s.equals(r.result()) ? Either.right(r) : Either.left(new ParserError("unexpected char '" + r.result() + "'")));
    }

    public static <I> Parser<I, Eps> skip(Parser<I, ?> p) {
        return then(p, eps());
    }

    public static <I, S> Parser<I, S> then(Parser<I, ?> p1, Parser<I, ? extends S> p2) {
        Objects.requireNonNull(p1);
        Objects.requireNonNull(p2);
        return p1.bind(r1 -> bindAlways(p2, identity()));
    }

    public static <I, R> Parser<I, R> thenSkip(Parser<I, ? extends R> p1, Parser<I, ?> p2) {
        Objects.requireNonNull(p1);
        Objects.requireNonNull(p2);
        return p1.bind(r1 -> bindAlways(p2, r2 -> r1));
    }

    public static <I, R, S> Parser<I, Pair<R, S>> pairThen(Parser<I, ? extends R> p1, Parser<I, ? extends S> p2) {
        Objects.requireNonNull(p1);
        Objects.requireNonNull(p2);
        return p1.bind(r1 -> bindAlways(p2, r2 -> Pair.of(r1, r2)));
    }

    public static <I, R> Parser<I, R> or(Parser<I, ? extends R> p1, Parser<I, ? extends R> p2) {
        Objects.requireNonNull(p1);
        Objects.requireNonNull(p2);
        return in -> {
            var r1 = p1.parse(in);
            if (r1.isRight()) {
                return Either.right(ParserResult.of(r1.tryGetRight().remaining(), r1.tryGetRight().result()));
            }

            var r2 = p2.parse(in);
            if (r2.isRight()) {
                return Either.right(ParserResult.of(r2.tryGetRight().remaining(), r2.tryGetRight().result()));
            }

            ParserError e = new ParserError("cannot match or");
            e.addSuppressed(r1.tryGetLeft());
            e.addSuppressed(r2.tryGetLeft());
            return Either.left(e);
        };
    }

    public static <I, R, S> Parser<I, Either<R, S>> eitherOr(Parser<I, ? extends R> p1, Parser<I, ? extends S> p2) {
        Objects.requireNonNull(p1);
        Objects.requireNonNull(p2);
        return in -> {
            var r1 = p1.parse(in);
            if (r1.isRight()) {
                return Either.right(ParserResult.of(r1.tryGetRight().remaining(), Either.left(r1.tryGetRight().result())));
            }

            var r2 = p2.parse(in);
            if (r2.isRight()) {
                return Either.right(ParserResult.of(r2.tryGetRight().remaining(), Either.right(r2.tryGetRight().result())));
            }

            ParserError e = new ParserError("cannot match or");
            e.addSuppressed(r1.tryGetLeft());
            e.addSuppressed(r2.tryGetLeft());
            return Either.left(e);
        };
    }

    public static <I, R> Parser<I, Optional<R>> optional(Parser<I, ? extends R> p) {
        Objects.requireNonNull(p);
        return in -> {
            var r = p.parse(in);
            if (r.isLeft()) {
                return Either.right(ParserResult.of(in, Optional.empty()));
            }

            return Either.right(r.tryGetRight().map(Optional::of));
        };
    }

    public static <I, R> Parser<I, List<R>> zeroOrMoreList(Parser<I, ? extends R> p) {
        return zeroOrMoreList(p, eps());
    }

    public static <I, R> Parser<I, List<R>> zeroOrMoreList(Parser<I, ? extends R> p, Parser<I, ?> separator) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(separator);
        return zeroOrMore(p, separator, ArrayList::new, (l, r) -> {
            l.add(r);
            return l;
        });
    }

    public static <I, R, S> Parser<I, S> zeroOrMore(Parser<I, ? extends R> p, Supplier<? extends S> identity, BiFunction<? super S, ? super R, ? extends S> acc) {
        return zeroOrMore(p, eps(), identity, acc);
    }

    public static <I, R, S> Parser<I, S> zeroOrMore(Parser<I, ? extends R> p, Parser<I, ?> separator, Supplier<? extends S> identity, BiFunction<? super S, ? super R, ? extends S> acc) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(separator);
        Objects.requireNonNull(identity);
        Objects.requireNonNull(acc);
        return in -> {
            S value = identity.get();
            for (Either<ParserError, ? extends ParserResult<I, ? extends R>> r = p.parse(in); r.isRight(); ) {
                value = acc.apply(value, r.tryGetRight().result());
                in = r.tryGetRight().remaining();

                var sr = separator.parse(in);
                if (sr.isLeft()) {
                    break;
                }

                r = p.parse(sr.tryGetRight().remaining());
            }

            return Either.right(ParserResult.of(in, value));
        };
    }

    public static <I, R> Parser<I, List<R>> oneOrMoreList(Parser<I, ? extends R> p) {
        return oneOrMoreList(p, eps());
    }

    public static <I, R> Parser<I, List<R>> oneOrMoreList(Parser<I, ? extends R> p, Parser<I, ?> separator) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(separator);
        return oneOrMore(p, separator, ArrayList::new, (l, r) -> {
            l.add(r);
            return l;
        });
    }

    public static <I, R, T> Parser<I, R> oneOrMore(Parser<I, ? extends T> p, Supplier<? extends R> identity, BiFunction<? super R, ? super T, ? extends R> acc) {
        return oneOrMore(p, eps(), identity, acc);
    }

    public static <I, R, T> Parser<I, R> oneOrMore(Parser<I, ? extends T> p, Parser<I, ?> separator, Supplier<? extends R> identity, BiFunction<? super R, ? super T, ? extends R> acc) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(separator);
        Objects.requireNonNull(identity);
        Objects.requireNonNull(acc);
        return in -> {
            R value = identity.get();
            Either<ParserError, ? extends ParserResult<I, ? extends T>> r = p.parse(in);
            if (r.isLeft()) {
                return Either.left(new ParserError("cannot match one or more", r.tryGetLeft()));
            }

            value = acc.apply(value, r.tryGetRight().result());
            in = r.tryGetRight().remaining();

            for (r = p.parse(in); r.isRight(); ) {
                value = acc.apply(value, r.tryGetRight().result());
                in = r.tryGetRight().remaining();

                var sr = separator.parse(in);
                if (sr.isLeft()) {
                    break;
                }

                r = p.parse(sr.tryGetRight().remaining());
            }

            return Either.right(ParserResult.of(in, value));
        };
    }

    public static Parser<Character, Character> character() {
        return in -> in.isEmpty() ? Either.left(new ParserError("unexpected eof")) : Either.right(ParserResult.of(in.next(), in.get()));
    }

    public static Parser<Character, Character> character(char c) {
        return character(c_ -> c_ == c);
    }

    public static Parser<Character, Character> character(CharPredicate c) {
        return in -> character().parse(in).flatMapRight(r -> c.test(r.result()) ? Either.right(r) : Either.left(new ParserError("unexpected char '" + r.result() + "'")));
    }

    public static Parser<Character, String> string(String s) {
        return regex(Pattern.compile(s, Pattern.LITERAL));
    }

    public static Parser<Character, String> regex(String p) {
        return regex(Pattern.compile(p));
    }

    public static Parser<Character, String> regex(Pattern p) {
        return regexResult(p).map(MatchResult::group);
    }

    public static Parser<Character, MatchResult> regexResult(Pattern p) {
        Objects.requireNonNull(p);
        return in -> {
            if (!(in instanceof CharInput cin)) {
                throw new IllegalArgumentException("regex is only supported for CharInput");
            }

            CharSequence s = cin.seq();
            Matcher m = p.matcher(s);
            if (m.lookingAt()) {
                return Either.right(ParserResult.of(in.next(m.end()), m.toMatchResult()));
            }

            boolean literal = (p.flags() & Pattern.LITERAL) != 0;
            int len = Math.min(s.length(), literal ? p.pattern().length() : 1);
            return Either.left(new ParserError("expected string " + (literal ? "" : "matching regex ") + "'" + p + "', but got " + (in.isEmpty() ? "eof" : "'" + s.subSequence(0, len) + "'")));
        };
    }

    public static Parser<Character, Character> digit() {
        return character(c -> '0' <= c && c <= '9');
    }

    public static Parser<Character, Integer> integer() {
        return oneOrMore(digit(), () -> 0, (a, e) -> Math.addExact(Math.multiplyExact(a, 10), e - '0'));
    }

    public static Parser<Character, BigInteger> bigInteger() {
        return oneOrMore(digit(), () -> BigIntegerConstants.ZERO, (a, e) -> a.multiply(BigIntegerConstants.TEN).add(BigIntegerMath.of(e - '0')));
    }

    public static Parser<Character, Eps> whitespace() {
        return zeroOrMore(character(Character::isWhitespace), () -> EPS, (a, e) -> a);
    }

    public static <I, R> ParserRef<I, R> ref() {
        return new ParserRef<>() {

            private Parser<I, R> p;

            @Override
            @SuppressWarnings("unchecked")
            public void set(Parser<I, ? extends R> p) {
                this.p = (Parser<I, R>) p;
            }

            @Override
            public Parser<I, R> get() {
                return p;
            }
        };
    }
}
