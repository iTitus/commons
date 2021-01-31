package io.github.ititus.math.number;

import io.github.ititus.converter.NumberConverter;
import org.assertj.core.data.Offset;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.ititus.assertions.Assertions.assertThat;
import static io.github.ititus.math.number.BigRational.ofExp;
import static io.github.ititus.math.number.BigRationalConstants.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.data.Offset.strictOffset;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BigRationalInexactOperationTests {

    private static final Offset<BigRational> EPS = strictOffset(ofExp(1, -31));

    static Stream<Arguments> test_sqrt() {
        return Stream.of(
                arguments(ZERO, ZERO),
                arguments(ONE, ONE),
                arguments(TWO, "1.414213562373095048801688724209698078569671875376948073176679738")
        );
    }

    static Stream<Arguments> test_sqrt_negative() {
        return Stream.of(
                arguments(MINUS_ONE)
        );
    }

    static Stream<Arguments> test_pow_inexact() {
        return Stream.of(
                arguments(ZERO, ZERO, ONE)
        );
    }

    static Stream<Arguments> test_exp() {
        return Stream.of(
                arguments(ZERO, ONE),
                arguments(ONE, "2.718281828459045235360287471352662497757247093699959574966967628"),
                arguments(MINUS_ONE, "0.3678794411714423215955237701614608674458111310317678345078368017"),
                arguments(TWO, "7.389056098930650227230427460575007813180315570551847324087127823"),
                arguments(E, "15.15426224147926418976043027262991190552854853685613976914074641")
        );
    }

    static Stream<Arguments> test_ln() {
        return Stream.of(
                arguments(ONE, ZERO),
                arguments(TWO, "0.6931471805599453094172321214581765680755001343602552541206800095"),
                arguments(E, 1),
                arguments(THREE, "1.098612288668109691395245236922525704647490557822749451734694334"),
                arguments(FOUR, "1.386294361119890618834464242916353136151000268720510508241360019"),
                arguments(FIVE, "1.609437912434100374600759333226187639525601354268517721912647891"),
                arguments(TEN, "2.302585092994045684017991454684364207601101488628772976033327901"),
                arguments(100, "4.605170185988091368035982909368728415202202977257545952066655802"),
                arguments(1000, "6.907755278982137052053974364053092622803304465886318928099983703")
        );
    }

    static Stream<Arguments> test_ln_not_positive() {
        return Stream.of(
                arguments(ZERO),
                arguments(MINUS_ONE)
        );
    }

    static Stream<Arguments> test_sin() {
        return Stream.of(
                arguments(ZERO, ZERO),
                arguments(ONE, "0.8414709848078965066525023216302989996225630607983710656727517100"),
                arguments(MINUS_ONE, "-0.8414709848078965066525023216302989996225630607983710656727517100"),
                arguments(PI_OVER_TWO, 1),
                arguments(MINUS_PI_OVER_TWO, -1),
                arguments(TWO, "0.9092974268256816953960198659117448427022549714478902683789730115"),
                arguments(MINUS_TWO, "-0.9092974268256816953960198659117448427022549714478902683789730115"),
                arguments(THREE, "0.1411200080598672221007448028081102798469332642522655841518826412"),
                arguments(MINUS_THREE, "-0.1411200080598672221007448028081102798469332642522655841518826412"),
                arguments(PI, 0),
                arguments(MINUS_PI, 0),
                arguments(FOUR, "-0.7568024953079282513726390945118290941359128873364725714854167734"),
                arguments(MINUS_FOUR, "0.7568024953079282513726390945118290941359128873364725714854167734"),
                arguments(PI_OVER_TWO.multiply(3), -1),
                arguments(MINUS_PI_OVER_TWO.multiply(3), 1),
                arguments(FIVE, "-0.9589242746631384688931544061559939733524615439646017781316724542"),
                arguments(MINUS_FIVE, "0.9589242746631384688931544061559939733524615439646017781316724542"),
                arguments(SIX, "-0.2794154981989258728115554466118947596279948643182043184833513697"),
                arguments(MINUS_SIX, "0.2794154981989258728115554466118947596279948643182043184833513697"),
                arguments(TWO_PI, 0),
                arguments(MINUS_TWO_PI, 0)
        );
    }

    static Stream<Arguments> test_atan() {
        return Stream.of(
                arguments(ZERO, ZERO),
                arguments(ONE, "0.7853981633974483096156608458198757210492923498437764552437361481"),
                arguments(MINUS_ONE, "-0.7853981633974483096156608458198757210492923498437764552437361481"),
                arguments(TWO, "1.107148717794090503017065460178537040070047645401432646676539207"),
                arguments(MINUS_TWO, "-1.107148717794090503017065460178537040070047645401432646676539207"),
                arguments(TEN, "1.471127674303734591852875571761730851855306377183238262471963519"),
                arguments(MINUS_TEN, "-1.471127674303734591852875571761730851855306377183238262471963519"),
                arguments(100, "1.560796660108231381024981575430471893537215347143176270859532878"),
                arguments(-100, "-1.560796660108231381024981575430471893537215347143176270859532878"),
                arguments(1000, "1.569796327128229752564797882004830898086963765133284897396041248"),
                arguments(-1000, "-1.569796327128229752564797882004830898086963765133284897396041248")
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_sqrt(@ConvertWith(NumberConverter.class) BigRational r,
                   @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.sqrt();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @ParameterizedTest
    @MethodSource
    void test_sqrt_negative(@ConvertWith(NumberConverter.class) BigRational r) {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(r::sqrt);
    }

    @ParameterizedTest
    @MethodSource
    void test_pow_inexact(@ConvertWith(NumberConverter.class) BigRational r,
                          @ConvertWith(NumberConverter.class) BigRational q,
                          @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.pow(q);
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @ParameterizedTest
    @MethodSource
    void test_exp(@ConvertWith(NumberConverter.class) BigRational r,
                  @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.exp();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @ParameterizedTest
    @MethodSource
    void test_ln(@ConvertWith(NumberConverter.class) BigRational r,
                 @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.ln();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @ParameterizedTest
    @MethodSource
    void test_ln_not_positive(@ConvertWith(NumberConverter.class) BigRational r) {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(r::ln);
    }

    @ParameterizedTest
    @MethodSource
    void test_sin(@ConvertWith(NumberConverter.class) BigRational r,
                  @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.sin();
        assertThat(actual).isCloseTo(expected, EPS);
    }

    @ParameterizedTest
    @MethodSource
    void test_atan(@ConvertWith(NumberConverter.class) BigRational r,
                   @ConvertWith(NumberConverter.class) BigRational expected) {
        BigRational actual = r.atan();
        assertThat(actual).isCloseTo(expected, EPS);
    }
}
