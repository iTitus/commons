package io.github.ititus.math.number;

import io.github.ititus.data.ArrayUtil;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Objects;

import static io.github.ititus.math.number.BigDecimalConstants.ONE;

public final class BigDecimalMath {

    public static MathContext MC = MathContext.DECIMAL128;

    private BigDecimalMath() {
    }

    public static BigDecimal of(Object o) {
        Objects.requireNonNull(o);
        if (o instanceof BigDecimal) {
            return of((BigDecimal) o);
        } else if (o instanceof String) {
            return of((String) o);
        } else if (o instanceof Integer) {
            return of((int) o);
        } else if (o instanceof Long) {
            return of((long) o);
        } else if (o instanceof BigInteger) {
            return of((BigInteger) o);
        } else if (o instanceof Float) {
            return of((float) o);
        } else if (o instanceof Double) {
            return of((double) o);
        } else if (o instanceof BigRational) {
            return of((BigRational) o);
        } else if (o instanceof BigComplex) {
            return of((BigComplex) o);
        } else if (o instanceof Collection) {
            Collection<?> c = (Collection<?>) o;
            if (c.size() == 1) {
                try {
                    return of(c.iterator().next());
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(o + " cannot be converted to BigDecimal", e);
                }
            }
        } else if (o.getClass().isArray()) {
            if (Array.getLength(o) == 1) {
                try {
                    return of(Array.get(o, 0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(ArrayUtil.toString(o) + " cannot be converted to BigDecimal", e);
                }
            }
        }

        throw new IllegalArgumentException(o + " cannot be converted to BigDecimal");
    }

    public static BigDecimal of(BigDecimal d) {
        return Objects.requireNonNull(d);
    }

    public static BigDecimal of(int n) {
        return BigDecimal.valueOf(n);
    }

    public static BigDecimal of(long n) {
        return BigDecimal.valueOf(n);
    }

    public static BigDecimal of(BigInteger n) {
        if (BigIntegerMath.isInt(n)) {
            return of(n.intValueExact());
        } else if (BigIntegerMath.isLong(n)) {
            return of(n.longValueExact());
        }

        return new BigDecimal(n);
    }

    public static BigDecimal of(float f) {
        return BigDecimal.valueOf(f);
    }

    public static BigDecimal of(double d) {
        return BigDecimal.valueOf(d);
    }

    public static BigDecimal of(BigRational r) {
        return r.toBigDecimal();
    }

    public static BigDecimal of(BigComplex z) {
        return BigRational.of(z).toBigDecimal();
    }

    public static BigDecimal of(String s) {
        return new BigDecimal(s);
    }

    public static boolean isBigInteger(BigDecimal d) {
        try {
            //noinspection ResultOfMethodCallIgnored
            d.toBigIntegerExact();
            return true;
        } catch (ArithmeticException ignored) {
            return false;
        }
    }

    public static BigDecimal inverseExact(BigDecimal d) {
        return ONE.divide(d, RoundingMode.UNNECESSARY);
    }

    public static BigDecimal inverse(BigDecimal d) {
        return inverse(d, MC);
    }

    public static BigDecimal inverse(BigDecimal d, RoundingMode rm) {
        return ONE.divide(d, rm);
    }

    public static BigDecimal inverse(BigDecimal d, MathContext mc) {
        return ONE.divide(d, mc);
    }
}
