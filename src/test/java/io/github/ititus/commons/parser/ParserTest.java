package io.github.ititus.commons.parser;

import io.github.ititus.commons.data.either.Either;
import io.github.ititus.commons.data.pair.ObjObjPair;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

import static io.github.ititus.commons.parser.Eps.EPS;
import static io.github.ititus.commons.parser.Parsers.*;
import static org.assertj.core.api.Assertions.assertThat;

class ParserTest {

    @Test
    void testEps() {
        Parser<Character, Eps> p = eps();

        assertThat(p.tryParse(Input.of(""))).isPresent();
        assertThat(p.tryParse(Input.of("a"))).isPresent();
        assertThat(p.tryParse(Input.of("aa"))).isPresent();
    }

    @Test
    void testEmpty() {
        Parser<Character, Eps> p = empty();

        assertThat(p.tryParse(Input.of(""))).isPresent();
        assertThat(p.tryParse(Input.of("a"))).isEmpty();
        assertThat(p.tryParse(Input.of("aa"))).isEmpty();
    }

    @Test
    void testCharacter() {
        var p = character('a');

        assertThat(p.tryParse(Input.of("a"))).isPresent();
        assertThat(p.tryParse(Input.of("b"))).isEmpty();
    }

    @Test
    void testDigit() {
        var p = digit();

        assertThat(p.tryParse(Input.of("0"))).isPresent();
        assertThat(p.tryParse(Input.of("1"))).isPresent();
        assertThat(p.tryParse(Input.of("2"))).isPresent();
        assertThat(p.tryParse(Input.of("3"))).isPresent();
        assertThat(p.tryParse(Input.of("4"))).isPresent();
        assertThat(p.tryParse(Input.of("5"))).isPresent();
        assertThat(p.tryParse(Input.of("6"))).isPresent();
        assertThat(p.tryParse(Input.of("7"))).isPresent();
        assertThat(p.tryParse(Input.of("8"))).isPresent();
        assertThat(p.tryParse(Input.of("9"))).isPresent();
        assertThat(p.tryParse(Input.of(""))).isEmpty();
        assertThat(p.tryParse(Input.of("a"))).isEmpty();
    }

    @Test
    void testInteger() {
        var p = integer();

        assertThat(p.tryParse(Input.of(""))).isEmpty();
        assertThat(p.tryParse(Input.of("0"))).contains(ParserResult.of(Input.of(""), 0));
        assertThat(p.tryParse(Input.of("1"))).contains(ParserResult.of(Input.of(""), 1));
        assertThat(p.tryParse(Input.of("10"))).contains(ParserResult.of(Input.of(""), 10));
        assertThat(p.tryParse(Input.of("a0"))).isEmpty();
        assertThat(p.tryParse(Input.of("a1"))).isEmpty();
        assertThat(p.tryParse(Input.of("a10"))).isEmpty();
        assertThat(p.tryParse(Input.of("0a"))).contains(ParserResult.of(Input.of("a"), 0));
        assertThat(p.tryParse(Input.of("1a"))).contains(ParserResult.of(Input.of("a"), 1));
        assertThat(p.tryParse(Input.of("10a"))).contains(ParserResult.of(Input.of("a"), 10));
    }

    @Test
    void testString() {
        var p = string("foo");

        assertThat(p.tryParse(Input.of("foo"))).isPresent();
        assertThat(p.tryParse(Input.of("fooa"))).isPresent();
        assertThat(p.tryParse(Input.of(""))).isEmpty();
        assertThat(p.tryParse(Input.of("a"))).isEmpty();
        assertThat(p.tryParse(Input.of("f"))).isEmpty();
        assertThat(p.tryParse(Input.of("fo"))).isEmpty();
        assertThat(p.tryParse(Input.of("bar"))).isEmpty();
        assertThat(p.tryParse(Input.of("afoo"))).isEmpty();
    }

    @Test
    void testWhitespace() {
        var p = whitespace();

        assertThat(p.tryParse(Input.of(""))).contains(ParserResult.of(Input.of(""), EPS));
        assertThat(p.tryParse(Input.of(" "))).contains(ParserResult.of(Input.of(""), EPS));
        assertThat(p.tryParse(Input.of("a"))).contains(ParserResult.of(Input.of("a"), EPS));
        assertThat(p.tryParse(Input.of("  "))).contains(ParserResult.of(Input.of(""), EPS));
        assertThat(p.tryParse(Input.of(" a"))).contains(ParserResult.of(Input.of("a"), EPS));
        assertThat(p.tryParse(Input.of("a "))).contains(ParserResult.of(Input.of("a "), EPS));
        assertThat(p.tryParse(Input.of("aa"))).contains(ParserResult.of(Input.of("aa"), EPS));
    }

    @Test
    void testEnd() {
        var p = character('a').end();

        assertThat(p.tryParse(Input.of("a"))).contains(ParserResult.of(Input.of(""), 'a'));
        assertThat(p.tryParse(Input.of(""))).isEmpty();
        assertThat(p.tryParse(Input.of("b"))).isEmpty();
        assertThat(p.tryParse(Input.of("aa"))).isEmpty();
        assertThat(p.tryParse(Input.of("ab"))).isEmpty();
    }

    @Test
    void testpairThen() {
        var p = character('a').pairThen(character('b'));

        assertThat(p.tryParse(Input.of(""))).isEmpty();

        assertThat(p.tryParse(Input.of("a"))).isEmpty();
        assertThat(p.tryParse(Input.of("b"))).isEmpty();
        assertThat(p.tryParse(Input.of("c"))).isEmpty();

        assertThat(p.tryParse(Input.of("aa"))).isEmpty();
        assertThat(p.tryParse(Input.of("ab"))).contains(ParserResult.of(Input.of(""), ObjObjPair.of('a', 'b')));
        assertThat(p.tryParse(Input.of("ac"))).isEmpty();
        assertThat(p.tryParse(Input.of("ba"))).isEmpty();
        assertThat(p.tryParse(Input.of("bb"))).isEmpty();
        assertThat(p.tryParse(Input.of("bc"))).isEmpty();
        assertThat(p.tryParse(Input.of("ca"))).isEmpty();
        assertThat(p.tryParse(Input.of("cb"))).isEmpty();
        assertThat(p.tryParse(Input.of("cc"))).isEmpty();

        assertThat(p.tryParse(Input.of("aaa"))).isEmpty();
        assertThat(p.tryParse(Input.of("aab"))).isEmpty();
        assertThat(p.tryParse(Input.of("aac"))).isEmpty();
        assertThat(p.tryParse(Input.of("aba"))).contains(ParserResult.of(Input.of("a"), ObjObjPair.of('a', 'b')));
        assertThat(p.tryParse(Input.of("abb"))).contains(ParserResult.of(Input.of("b"), ObjObjPair.of('a', 'b')));
        assertThat(p.tryParse(Input.of("abc"))).contains(ParserResult.of(Input.of("c"), ObjObjPair.of('a', 'b')));
        assertThat(p.tryParse(Input.of("aca"))).isEmpty();
        assertThat(p.tryParse(Input.of("acb"))).isEmpty();
        assertThat(p.tryParse(Input.of("acc"))).isEmpty();
        assertThat(p.tryParse(Input.of("baa"))).isEmpty();
        assertThat(p.tryParse(Input.of("bab"))).isEmpty();
        assertThat(p.tryParse(Input.of("bac"))).isEmpty();
        assertThat(p.tryParse(Input.of("bba"))).isEmpty();
        assertThat(p.tryParse(Input.of("bbb"))).isEmpty();
        assertThat(p.tryParse(Input.of("bbc"))).isEmpty();
        assertThat(p.tryParse(Input.of("bca"))).isEmpty();
        assertThat(p.tryParse(Input.of("bcb"))).isEmpty();
        assertThat(p.tryParse(Input.of("bcc"))).isEmpty();
        assertThat(p.tryParse(Input.of("cca"))).isEmpty();
        assertThat(p.tryParse(Input.of("ccb"))).isEmpty();
        assertThat(p.tryParse(Input.of("ccc"))).isEmpty();
        assertThat(p.tryParse(Input.of("caa"))).isEmpty();
        assertThat(p.tryParse(Input.of("cab"))).isEmpty();
        assertThat(p.tryParse(Input.of("cac"))).isEmpty();
        assertThat(p.tryParse(Input.of("cba"))).isEmpty();
        assertThat(p.tryParse(Input.of("cbb"))).isEmpty();
        assertThat(p.tryParse(Input.of("cbc"))).isEmpty();
        assertThat(p.tryParse(Input.of("cca"))).isEmpty();
        assertThat(p.tryParse(Input.of("ccb"))).isEmpty();
        assertThat(p.tryParse(Input.of("ccc"))).isEmpty();
    }

    @Test
    void testOr() {
        var p = character('a').or(character('b'));

        assertThat(p.tryParse(Input.of(""))).isEmpty();

        assertThat(p.tryParse(Input.of("a"))).contains(ParserResult.of(Input.of(""), 'a'));
        assertThat(p.tryParse(Input.of("b"))).contains(ParserResult.of(Input.of(""), 'b'));
        assertThat(p.tryParse(Input.of("c"))).isEmpty();

        assertThat(p.tryParse(Input.of("aa"))).contains(ParserResult.of(Input.of("a"), 'a'));
        assertThat(p.tryParse(Input.of("ab"))).contains(ParserResult.of(Input.of("b"), 'a'));
        assertThat(p.tryParse(Input.of("ac"))).contains(ParserResult.of(Input.of("c"), 'a'));
        assertThat(p.tryParse(Input.of("ba"))).contains(ParserResult.of(Input.of("a"), 'b'));
        assertThat(p.tryParse(Input.of("bb"))).contains(ParserResult.of(Input.of("b"), 'b'));
        assertThat(p.tryParse(Input.of("bc"))).contains(ParserResult.of(Input.of("c"), 'b'));
        assertThat(p.tryParse(Input.of("ca"))).isEmpty();
        assertThat(p.tryParse(Input.of("cb"))).isEmpty();
        assertThat(p.tryParse(Input.of("cb"))).isEmpty();

        assertThat(p.tryParse(Input.of("aaa"))).contains(ParserResult.of(Input.of("aa"), 'a'));
        assertThat(p.tryParse(Input.of("aab"))).contains(ParserResult.of(Input.of("ab"), 'a'));
        assertThat(p.tryParse(Input.of("aac"))).contains(ParserResult.of(Input.of("ac"), 'a'));
        assertThat(p.tryParse(Input.of("aba"))).contains(ParserResult.of(Input.of("ba"), 'a'));
        assertThat(p.tryParse(Input.of("abb"))).contains(ParserResult.of(Input.of("bb"), 'a'));
        assertThat(p.tryParse(Input.of("abc"))).contains(ParserResult.of(Input.of("bc"), 'a'));
        assertThat(p.tryParse(Input.of("aca"))).contains(ParserResult.of(Input.of("ca"), 'a'));
        assertThat(p.tryParse(Input.of("acb"))).contains(ParserResult.of(Input.of("cb"), 'a'));
        assertThat(p.tryParse(Input.of("acc"))).contains(ParserResult.of(Input.of("cc"), 'a'));
        assertThat(p.tryParse(Input.of("baa"))).contains(ParserResult.of(Input.of("aa"), 'b'));
        assertThat(p.tryParse(Input.of("bab"))).contains(ParserResult.of(Input.of("ab"), 'b'));
        assertThat(p.tryParse(Input.of("bac"))).contains(ParserResult.of(Input.of("ac"), 'b'));
        assertThat(p.tryParse(Input.of("bba"))).contains(ParserResult.of(Input.of("ba"), 'b'));
        assertThat(p.tryParse(Input.of("bbb"))).contains(ParserResult.of(Input.of("bb"), 'b'));
        assertThat(p.tryParse(Input.of("bbc"))).contains(ParserResult.of(Input.of("bc"), 'b'));
        assertThat(p.tryParse(Input.of("bca"))).contains(ParserResult.of(Input.of("ca"), 'b'));
        assertThat(p.tryParse(Input.of("bcb"))).contains(ParserResult.of(Input.of("cb"), 'b'));
        assertThat(p.tryParse(Input.of("bcc"))).contains(ParserResult.of(Input.of("cc"), 'b'));
        assertThat(p.tryParse(Input.of("cca"))).isEmpty();
        assertThat(p.tryParse(Input.of("ccb"))).isEmpty();
        assertThat(p.tryParse(Input.of("ccc"))).isEmpty();
        assertThat(p.tryParse(Input.of("caa"))).isEmpty();
        assertThat(p.tryParse(Input.of("cab"))).isEmpty();
        assertThat(p.tryParse(Input.of("cac"))).isEmpty();
        assertThat(p.tryParse(Input.of("cba"))).isEmpty();
        assertThat(p.tryParse(Input.of("cbb"))).isEmpty();
        assertThat(p.tryParse(Input.of("cbc"))).isEmpty();
        assertThat(p.tryParse(Input.of("cca"))).isEmpty();
        assertThat(p.tryParse(Input.of("ccb"))).isEmpty();
        assertThat(p.tryParse(Input.of("ccc"))).isEmpty();
    }

    @Test
    void testEitherOr() {
        var p = character('a').eitherOr(character('b'));

        assertThat(p.tryParse(Input.of(""))).isEmpty();

        assertThat(p.tryParse(Input.of("a"))).contains(ParserResult.of(Input.of(""), Either.left('a')));
        assertThat(p.tryParse(Input.of("b"))).contains(ParserResult.of(Input.of(""), Either.right('b')));
        assertThat(p.tryParse(Input.of("c"))).isEmpty();

        assertThat(p.tryParse(Input.of("aa"))).contains(ParserResult.of(Input.of("a"), Either.left('a')));
        assertThat(p.tryParse(Input.of("ab"))).contains(ParserResult.of(Input.of("b"), Either.left('a')));
        assertThat(p.tryParse(Input.of("ac"))).contains(ParserResult.of(Input.of("c"), Either.left('a')));
        assertThat(p.tryParse(Input.of("ba"))).contains(ParserResult.of(Input.of("a"), Either.right('b')));
        assertThat(p.tryParse(Input.of("bb"))).contains(ParserResult.of(Input.of("b"), Either.right('b')));
        assertThat(p.tryParse(Input.of("bc"))).contains(ParserResult.of(Input.of("c"), Either.right('b')));
        assertThat(p.tryParse(Input.of("ca"))).isEmpty();
        assertThat(p.tryParse(Input.of("cb"))).isEmpty();
        assertThat(p.tryParse(Input.of("cb"))).isEmpty();

        assertThat(p.tryParse(Input.of("aaa"))).contains(ParserResult.of(Input.of("aa"), Either.left('a')));
        assertThat(p.tryParse(Input.of("aab"))).contains(ParserResult.of(Input.of("ab"), Either.left('a')));
        assertThat(p.tryParse(Input.of("aac"))).contains(ParserResult.of(Input.of("ac"), Either.left('a')));
        assertThat(p.tryParse(Input.of("aba"))).contains(ParserResult.of(Input.of("ba"), Either.left('a')));
        assertThat(p.tryParse(Input.of("abb"))).contains(ParserResult.of(Input.of("bb"), Either.left('a')));
        assertThat(p.tryParse(Input.of("abc"))).contains(ParserResult.of(Input.of("bc"), Either.left('a')));
        assertThat(p.tryParse(Input.of("aca"))).contains(ParserResult.of(Input.of("ca"), Either.left('a')));
        assertThat(p.tryParse(Input.of("acb"))).contains(ParserResult.of(Input.of("cb"), Either.left('a')));
        assertThat(p.tryParse(Input.of("acc"))).contains(ParserResult.of(Input.of("cc"), Either.left('a')));
        assertThat(p.tryParse(Input.of("baa"))).contains(ParserResult.of(Input.of("aa"), Either.right('b')));
        assertThat(p.tryParse(Input.of("bab"))).contains(ParserResult.of(Input.of("ab"), Either.right('b')));
        assertThat(p.tryParse(Input.of("bac"))).contains(ParserResult.of(Input.of("ac"), Either.right('b')));
        assertThat(p.tryParse(Input.of("bba"))).contains(ParserResult.of(Input.of("ba"), Either.right('b')));
        assertThat(p.tryParse(Input.of("bbb"))).contains(ParserResult.of(Input.of("bb"), Either.right('b')));
        assertThat(p.tryParse(Input.of("bbc"))).contains(ParserResult.of(Input.of("bc"), Either.right('b')));
        assertThat(p.tryParse(Input.of("bca"))).contains(ParserResult.of(Input.of("ca"), Either.right('b')));
        assertThat(p.tryParse(Input.of("bcb"))).contains(ParserResult.of(Input.of("cb"), Either.right('b')));
        assertThat(p.tryParse(Input.of("bcc"))).contains(ParserResult.of(Input.of("cc"), Either.right('b')));
        assertThat(p.tryParse(Input.of("cca"))).isEmpty();
        assertThat(p.tryParse(Input.of("ccb"))).isEmpty();
        assertThat(p.tryParse(Input.of("ccc"))).isEmpty();
        assertThat(p.tryParse(Input.of("caa"))).isEmpty();
        assertThat(p.tryParse(Input.of("cab"))).isEmpty();
        assertThat(p.tryParse(Input.of("cac"))).isEmpty();
        assertThat(p.tryParse(Input.of("cba"))).isEmpty();
        assertThat(p.tryParse(Input.of("cbb"))).isEmpty();
        assertThat(p.tryParse(Input.of("cbc"))).isEmpty();
        assertThat(p.tryParse(Input.of("cca"))).isEmpty();
        assertThat(p.tryParse(Input.of("ccb"))).isEmpty();
        assertThat(p.tryParse(Input.of("ccc"))).isEmpty();
    }

    @Test
    void testZeroOrMoreList() {
        var p = character('a').zeroOrMoreList();

        assertThat(p.tryParse(Input.of(""))).contains(ParserResult.of(Input.of(""), List.of()));

        assertThat(p.tryParse(Input.of("a"))).contains(ParserResult.of(Input.of(""), List.of('a')));
        assertThat(p.tryParse(Input.of("b"))).contains(ParserResult.of(Input.of("b"), List.of()));

        assertThat(p.tryParse(Input.of("aa"))).contains(ParserResult.of(Input.of(""), List.of('a', 'a')));
        assertThat(p.tryParse(Input.of("ab"))).contains(ParserResult.of(Input.of("b"), List.of('a')));
        assertThat(p.tryParse(Input.of("ba"))).contains(ParserResult.of(Input.of("ba"), List.of()));
        assertThat(p.tryParse(Input.of("bb"))).contains(ParserResult.of(Input.of("bb"), List.of()));
    }

    @Test
    void testOneOrMoreList() {
        var p = character('a').oneOrMoreList();

        assertThat(p.tryParse(Input.of(""))).isEmpty();

        assertThat(p.tryParse(Input.of("a"))).contains(ParserResult.of(Input.of(""), List.of('a')));
        assertThat(p.tryParse(Input.of("b"))).isEmpty();

        assertThat(p.tryParse(Input.of("aa"))).contains(ParserResult.of(Input.of(""), List.of('a', 'a')));
        assertThat(p.tryParse(Input.of("ab"))).contains(ParserResult.of(Input.of("b"), List.of('a')));
        assertThat(p.tryParse(Input.of("ba"))).isEmpty();
        assertThat(p.tryParse(Input.of("bb"))).isEmpty();
    }

    @Test
    void testBalancedBracketParser() {
        // S -> (S)S | eps
        var open = character('(');
        var close = character(')');
        ParserRef<Character, Eps> ref = ref();
        ref.set(open.then(ref).then(close).then(ref).skip().or(eps()));
        var p = ref.end();

        assertThat(p.tryParse(Input.of(""))).isPresent();

        assertThat(p.tryParse(Input.of("("))).isEmpty();
        assertThat(p.tryParse(Input.of(")"))).isEmpty();

        assertThat(p.tryParse(Input.of("(("))).isEmpty();
        assertThat(p.tryParse(Input.of("()"))).isPresent();
        assertThat(p.tryParse(Input.of(")("))).isEmpty();
        assertThat(p.tryParse(Input.of("))"))).isEmpty();

        assertThat(p.tryParse(Input.of("((("))).isEmpty();
        assertThat(p.tryParse(Input.of("(()"))).isEmpty();
        assertThat(p.tryParse(Input.of("()("))).isEmpty();
        assertThat(p.tryParse(Input.of("())"))).isEmpty();
        assertThat(p.tryParse(Input.of(")(("))).isEmpty();
        assertThat(p.tryParse(Input.of(")()"))).isEmpty();
        assertThat(p.tryParse(Input.of("))("))).isEmpty();
        assertThat(p.tryParse(Input.of(")))"))).isEmpty();

        assertThat(p.tryParse(Input.of("(((("))).isEmpty();
        assertThat(p.tryParse(Input.of("((()"))).isEmpty();
        assertThat(p.tryParse(Input.of("(()("))).isEmpty();
        assertThat(p.tryParse(Input.of("(())"))).isPresent();
        assertThat(p.tryParse(Input.of("()(("))).isEmpty();
        assertThat(p.tryParse(Input.of("()()"))).isPresent();
        assertThat(p.tryParse(Input.of("())("))).isEmpty();
        assertThat(p.tryParse(Input.of("()))"))).isEmpty();
        assertThat(p.tryParse(Input.of(")((("))).isEmpty();
        assertThat(p.tryParse(Input.of(")(()"))).isEmpty();
        assertThat(p.tryParse(Input.of(")()("))).isEmpty();
        assertThat(p.tryParse(Input.of(")())"))).isEmpty();
        assertThat(p.tryParse(Input.of("))(("))).isEmpty();
        assertThat(p.tryParse(Input.of("))()"))).isEmpty();
        assertThat(p.tryParse(Input.of(")))("))).isEmpty();
        assertThat(p.tryParse(Input.of("))))"))).isEmpty();
    }

    @Test
    void testCalculator() {
        // E -> T (('+' | '-') T)*
        // T -> F (('*' | '/' | '%') F)*
        // F -> W ('+' | '-')? P
        // P -> W ('(' E ')' | integer) W
        // W -> ' '*

        Parser<Character, IntUnaryOperator> unaryPlus = character('+').bindAlways(c -> a -> +a);
        Parser<Character, IntUnaryOperator> unaryMinus = character('-').bindAlways(c -> a -> -a);
        Parser<Character, IntBinaryOperator> add = character('+').bindAlways(c -> Integer::sum);
        Parser<Character, IntBinaryOperator> sub = character('-').bindAlways(c -> (a, b) -> a - b);
        Parser<Character, IntBinaryOperator> mul = character('*').bindAlways(c -> (a, b) -> a * b);
        Parser<Character, IntBinaryOperator> div = character('/').bindAlways(c -> (a, b) -> a / b);
        Parser<Character, IntBinaryOperator> mod = character('%').bindAlways(c -> (a, b) -> a % b);
        var unary = unaryPlus.or(unaryMinus);
        var additive = add.or(sub);
        var multiplicative = mul.or(div).or(mod);
        var open = character('(').skip();
        var close = character(')').skip();

        ParserRef<Character, Integer> E = ref();
        var P = whitespace().then(open.then(E).thenSkip(close).or(integer())).thenSkip(whitespace());
        var F = whitespace().then(unary.optional()).bind(op -> P.bindAlways(a -> op.map(op_ -> op_.applyAsInt(a)).orElse(a)));
        var T = F.bind(n -> multiplicative.pairThen(F).zeroOrMore(() -> n, (a, p) -> p.a().applyAsInt(a, p.b())));
        E.set(T.bind(n -> additive.pairThen(T).zeroOrMore(() -> n, (a, p) -> p.a().applyAsInt(a, p.b()))));

        var p = E.end();

        assertThat(p.tryParse(Input.of(""))).isEmpty();
        assertThat(p.tryParse(Input.of("1"))).contains(ParserResult.of(Input.of(""), 1));
        assertThat(p.tryParse(Input.of("+1"))).contains(ParserResult.of(Input.of(""), 1));
        assertThat(p.tryParse(Input.of("-1"))).contains(ParserResult.of(Input.of(""), -1));
        assertThat(p.tryParse(Input.of("(1)"))).contains(ParserResult.of(Input.of(""), 1));
        assertThat(p.tryParse(Input.of("(+1)"))).contains(ParserResult.of(Input.of(""), 1));
        assertThat(p.tryParse(Input.of("(-1)"))).contains(ParserResult.of(Input.of(""), -1));
        assertThat(p.tryParse(Input.of("2 + 3"))).contains(ParserResult.of(Input.of(""), 5));
        assertThat(p.tryParse(Input.of("2 + -3"))).contains(ParserResult.of(Input.of(""), -1));
        assertThat(p.tryParse(Input.of("-2 + 3"))).contains(ParserResult.of(Input.of(""), 1));
        assertThat(p.tryParse(Input.of("-2 + -3"))).contains(ParserResult.of(Input.of(""), -5));
        assertThat(p.tryParse(Input.of("2 - 3"))).contains(ParserResult.of(Input.of(""), -1));
        assertThat(p.tryParse(Input.of("2 * 3"))).contains(ParserResult.of(Input.of(""), 6));
        assertThat(p.tryParse(Input.of("5 / 2"))).contains(ParserResult.of(Input.of(""), 2));
        assertThat(p.tryParse(Input.of("5 % 3"))).contains(ParserResult.of(Input.of(""), 2));
        assertThat(p.tryParse(Input.of("1 + 2 + 3"))).contains(ParserResult.of(Input.of(""), 6));
        assertThat(p.tryParse(Input.of("1 + 2 * 3"))).contains(ParserResult.of(Input.of(""), 7));
        assertThat(p.tryParse(Input.of("(1 + 2) * 3"))).contains(ParserResult.of(Input.of(""), 9));
    }
}
