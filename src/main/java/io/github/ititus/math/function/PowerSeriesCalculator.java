package io.github.ititus.math.function;

import io.github.ititus.math.number.BigIntegerMath;
import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;
import io.github.ititus.math.number.BigRationalMath;

import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public final class PowerSeriesCalculator {

    private static final MathContext FAST_APPROX_MC = new MathContext(2, RoundingMode.HALF_EVEN);

    private static final BigInteger N = BigIntegerMath.of(1_000);
    private static final ExpPowerSeries EXP = new ExpPowerSeries();
    private static final LnPowerSeries LN = new LnPowerSeries();
    private static final SinPowerSeries SIN = new SinPowerSeries();
    private static final CosPowerSeries COS = new CosPowerSeries();

    @SuppressWarnings("Duplicates")
    private static BigRational calculatePowerSeries(PowerSeries powerSeries, BigRational x, MathContext mc) {
        int precision = mc.getPrecision();
        if (precision == 0) {
            throw new ArithmeticException();
        }
        BigRational minDiff = BigRationalMath.pow(BigRationalConstants.TEN, BigIntegerMath.of(precision + 1)).inverse();
        x = x.round(mc);

        BigRational result = BigRationalConstants.ZERO;
        BigRational power = BigRationalConstants.ONE;

        for (BigInteger n = BigInteger.ZERO; n.compareTo(N) <= 0; n = n.add(BigInteger.ONE)) {
            BigRational coeff = powerSeries.getCoefficient(n).round(mc);
            if (!coeff.isZero()) {
                BigRational term = coeff.multiply(power).round(mc);

                if (term.abs().compareTo(minDiff) <= 0) {
                    break;
                }

                result = result.add(term).round(mc);
            }
            power = power.multiply(x).round(mc);
        }

        return result.round(mc);
    }

    @SuppressWarnings("Duplicates")
    private static BigRational calculateSeries(Series series, BigRational x, MathContext mc) {
        int precision = mc.getPrecision();
        if (precision == 0) {
            throw new ArithmeticException();
        }
        BigRational minDiff = BigRationalMath.pow(BigRationalConstants.TEN, BigIntegerMath.of(precision + 1)).inverse();
        x = x.round(mc);

        BigRational result = BigRationalConstants.ZERO;

        for (BigInteger n = BigInteger.ZERO; n.compareTo(N) <= 0; n = n.add(BigInteger.ONE)) {
            BigRational term = series.getTerm(n, x).round(mc);
            if (!term.isZero()) {
                if (term.abs().compareTo(minDiff) <= 0) {
                    break;
                }

                result = result.add(term).round(mc);
            }
        }

        return result.round(mc);
    }

    public static BigRational exp(BigRational x) {
        return calculatePowerSeries(EXP, x, MathContext.DECIMAL128);
    }

    public static BigRational ln(BigRational x) {
        return BigRationalConstants.TWO.multiply(calculatePowerSeries(LN,
                x.subtract(BigRationalConstants.ONE).divide(x.add(BigRationalConstants.ONE)), MathContext.DECIMAL128));
    }

    public static BigRational lnFast(BigRational x) {
        return BigRationalConstants.TWO.multiply(calculatePowerSeries(LN,
                x.subtract(BigRationalConstants.ONE).divide(x.add(BigRationalConstants.ONE)), FAST_APPROX_MC));
    }

    public static BigRational sin(BigRational x) {
        return calculatePowerSeries(SIN, x, MathContext.DECIMAL128);
    }

    public static BigRational cos(BigRational x) {
        return calculatePowerSeries(COS, x, MathContext.DECIMAL128);
    }

    private static abstract class Series {

        protected abstract BigRational getTerm(BigInteger n, BigRational x);

    }

    private static abstract class PowerSeries {

        protected final Map<BigInteger, BigRational> cache;

        protected PowerSeries() {
            this.cache = new HashMap<>();
        }

        protected abstract BigRational getCoefficient(BigInteger n);
    }

    private static class ExpPowerSeries extends PowerSeries {

        @Override
        protected BigRational getCoefficient(BigInteger n) {
            if (n.signum() == 0) {
                return BigRationalConstants.ONE;
            }

            return cache.computeIfAbsent(n,
                    n_ -> getCoefficient(n_.subtract(BigInteger.ONE)).divide(BigRational.of(n_)));
        }
    }

    private static class LnPowerSeries extends PowerSeries {

        @Override
        protected BigRational getCoefficient(BigInteger n) {
            if (BigIntegerMath.isEven(n)) {
                return BigRationalConstants.ZERO;
            }

            return BigRational.ofInv(n);
        }
    }

    private static class SinPowerSeries extends PowerSeries {

        @Override
        protected BigRational getCoefficient(BigInteger n) {
            if (n.equals(BigInteger.ONE)) {
                return BigRationalConstants.ONE;
            } else if (BigIntegerMath.isEven(n)) {
                return BigRationalConstants.ZERO;
            }

            return cache.computeIfAbsent(n,
                    n_ -> getCoefficient(n_.subtract(BigInteger.TWO)).divide(BigRational.of(n_.subtract(BigInteger.ONE))).divide(BigRational.of(n_)).negate());
        }
    }

    private static class CosPowerSeries extends PowerSeries {

        @Override
        protected BigRational getCoefficient(BigInteger n) {
            if (n.signum() == 0) {
                return BigRationalConstants.ONE;
            } else if (BigIntegerMath.isOdd(n)) {
                return BigRationalConstants.ZERO;
            }

            return cache.computeIfAbsent(n,
                    n_ -> getCoefficient(n_.subtract(BigInteger.TWO)).divide(BigRational.of(n_.subtract(BigInteger.ONE))).divide(BigRational.of(n_)).negate());
        }
    }
}
