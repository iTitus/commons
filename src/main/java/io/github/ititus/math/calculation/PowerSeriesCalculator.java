package io.github.ititus.math.calculation;

import io.github.ititus.math.number.BigIntegerConstants;
import io.github.ititus.math.number.BigIntegerMath;
import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;

import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static io.github.ititus.math.number.BigRationalConstants.ONE;
import static io.github.ititus.math.number.BigRationalConstants.ZERO;

public final class PowerSeriesCalculator {

    private static final MathContext FAST_APPROX_MC = new MathContext(2, RoundingMode.HALF_EVEN);

    private static final BigInteger MAX_ITER = BigIntegerMath.of(1_000);
    private static final ExpPowerSeries EXP = new ExpPowerSeries();
    private static final LnPowerSeries LN = new LnPowerSeries();
    private static final SinPowerSeries SIN = new SinPowerSeries();
    private static final CosPowerSeries COS = new CosPowerSeries();
    private static final AtanPowerSeries ATAN = new AtanPowerSeries();
    private static final SinhPowerSeries SINH = new SinhPowerSeries();
    private static final CoshPowerSeries COSH = new CoshPowerSeries();

    @SuppressWarnings("Duplicates")
    private static BigRational calculatePowerSeries(PowerSeries powerSeries, BigRational x, MathContext mc) {
        int precision = mc.getPrecision();
        if (precision == 0) {
            throw new ArithmeticException();
        }

        BigRational minDiff = BigRational.ofExp(1, -precision);
        x = x.round(mc);

        BigRational result = ZERO;
        BigRational power = ONE;

        for (BigInteger n = BigIntegerConstants.ZERO; n.compareTo(MAX_ITER) <= 0; n = n.add(BigIntegerConstants.ONE)) {
            BigRational coeff = powerSeries.getCoefficient(n)/*.round(mc)*/;
            if (!coeff.isZero()) {
                BigRational term = coeff.multiply(power)/*.round(mc)*/;

                if (term.abs().compareTo(minDiff) < 0) {
                    break;
                }

                result = result.add(term)/*.round(mc)*/;
            }
            power = power.multiply(x)/*.round(mc)*/;
        }

        return result.round(mc);
    }

    @SuppressWarnings("Duplicates")
    private static BigRational calculateSeries(Series series, BigRational x, MathContext mc) {
        int precision = mc.getPrecision();
        if (precision == 0) {
            throw new ArithmeticException();
        }
        BigRational minDiff = BigRational.ofExp(1, -precision);
        x = x.round(mc);

        BigRational result = ZERO;

        for (BigInteger n = BigIntegerConstants.ZERO; n.compareTo(MAX_ITER) <= 0; n = n.add(BigIntegerConstants.ONE)) {
            BigRational term = series.getTerm(n, x)/*.round(mc)*/;
            if (!term.isZero()) {
                if (term.abs().compareTo(minDiff) < 0) {
                    break;
                }

                result = result.add(term)/*.round(mc)*/;
            }
        }

        return result.round(mc);
    }

    public static BigRational exp(BigRational x) {
        return calculatePowerSeries(EXP, x, MathContext.DECIMAL128);
    }

    public static BigRational ln(BigRational x) {
        return BigRationalConstants.TWO.multiply(calculatePowerSeries(LN,
                x.subtract(ONE).divide(x.add(ONE)), MathContext.DECIMAL128));
    }

    public static BigRational lnFast(BigRational x) {
        return BigRationalConstants.TWO.multiply(calculatePowerSeries(LN,
                x.subtract(ONE).divide(x.add(ONE)), FAST_APPROX_MC));
    }

    public static BigRational sin(BigRational x) {
        return calculatePowerSeries(SIN, x, MathContext.DECIMAL128);
    }

    public static BigRational cos(BigRational x) {
        return calculatePowerSeries(COS, x, MathContext.DECIMAL128);
    }

    public static BigRational atan(BigRational x) {
        return calculatePowerSeries(ATAN, x, MathContext.DECIMAL128);
    }

    public static BigRational sinh(BigRational x) {
        return calculatePowerSeries(SINH, x, MathContext.DECIMAL128);
    }

    public static BigRational cosh(BigRational x) {
        return calculatePowerSeries(COSH, x, MathContext.DECIMAL128);
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
                return ONE;
            }

            return cache.computeIfAbsent(n,
                    n_ -> getCoefficient(n_.subtract(BigIntegerConstants.ONE)).divide(BigRational.of(n_)));
        }
    }

    private static class LnPowerSeries extends PowerSeries {

        @Override
        protected BigRational getCoefficient(BigInteger n) {
            if (BigIntegerMath.isEven(n)) {
                return ZERO;
            }

            return BigRational.ofInv(n);
        }
    }

    private static class SinPowerSeries extends PowerSeries {

        @Override
        protected BigRational getCoefficient(BigInteger n) {
            if (n.equals(BigIntegerConstants.ONE)) {
                return ONE;
            } else if (BigIntegerMath.isEven(n)) {
                return ZERO;
            }

            return cache.computeIfAbsent(n,
                    n_ -> getCoefficient(n_.subtract(BigIntegerConstants.TWO)).divide(BigRational.of(n_.subtract(BigIntegerConstants.ONE))).divide(BigRational.of(n_)).negate());
        }
    }

    private static class CosPowerSeries extends PowerSeries {

        @Override
        protected BigRational getCoefficient(BigInteger n) {
            if (n.signum() == 0) {
                return ONE;
            } else if (BigIntegerMath.isOdd(n)) {
                return ZERO;
            }

            return cache.computeIfAbsent(n,
                    n_ -> getCoefficient(n_.subtract(BigIntegerConstants.TWO)).divide(BigRational.of(n_.subtract(BigIntegerConstants.ONE))).divide(BigRational.of(n_)).negate());
        }
    }

    private static class AtanPowerSeries extends PowerSeries {

        @Override
        protected BigRational getCoefficient(BigInteger n) {
            if (n.equals(BigIntegerConstants.ONE)) {
                return ONE;
            } else if (BigIntegerMath.isEven(n)) {
                return ZERO;
            }

            BigRational result = BigRational.ofInv(n);
            return n.mod(BigIntegerConstants.FOUR).equals(BigIntegerConstants.ONE) ? result : result.negate();
        }
    }

    private static class SinhPowerSeries extends PowerSeries {

        @Override
        protected BigRational getCoefficient(BigInteger n) {
            if (n.equals(BigIntegerConstants.ONE)) {
                return ONE;
            } else if (BigIntegerMath.isEven(n)) {
                return ZERO;
            }

            return cache.computeIfAbsent(n,
                    n_ -> getCoefficient(n_.subtract(BigIntegerConstants.TWO)).divide(BigRational.of(n_.subtract(BigIntegerConstants.ONE))).divide(BigRational.of(n_)));
        }
    }

    private static class CoshPowerSeries extends PowerSeries {

        @Override
        protected BigRational getCoefficient(BigInteger n) {
            if (n.signum() == 0) {
                return ONE;
            } else if (BigIntegerMath.isOdd(n)) {
                return ZERO;
            }

            return cache.computeIfAbsent(n,
                    n_ -> getCoefficient(n_.subtract(BigIntegerConstants.TWO)).divide(BigRational.of(n_.subtract(BigIntegerConstants.ONE))).divide(BigRational.of(n_)));
        }
    }
}
