package io.github.ititus.math.number;

import java.math.BigInteger;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static io.github.ititus.math.number.BigIntegerConstants.ONE;
import static io.github.ititus.math.number.BigIntegerConstants.ZERO;

public final class BigIntegerMath {

    private static final NavigableMap<BigInteger, BigInteger> FACTORIAL_CACHE = new TreeMap<>();

    private BigIntegerMath() {
    }

    public static BigInteger of(int n) {
        return BigInteger.valueOf(n);
    }

    public static BigInteger of(long n) {
        return BigInteger.valueOf(n);
    }

    public static BigInteger of(String s) {
        return new BigInteger(s);
    }

    public static BigInteger of(String s, int radix) {
        return new BigInteger(s, radix);
    }

    @SuppressWarnings("Duplicates")
    public static BigInteger pow(BigInteger base, BigInteger exponent) {
        if (base.equals(ONE) || exponent.signum() == 0) {
            return ONE;
        } else if (base.signum() == 0) {
            return ZERO;
        } else if (exponent.signum() < 0) {
            throw new ArithmeticException();
        }

        BigInteger n = ONE;

        while (exponent.signum() > 0) {
            if (BigIntegerMath.isOdd(exponent)) {
                n = n.multiply(base);
                exponent = exponent.subtract(ONE);
            } else {
                base = base.multiply(base);
                exponent = exponent.shiftRight(1);
            }
        }

        return n;
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if (a.equals(ONE) || b.equals(ONE)) {
            return ONE;
        }

        return a.gcd(b);
    }

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        if (a.equals(ZERO) || b.equals(ZERO)) {
            throw new ArithmeticException();
        }

        return a.divide(gcd(a, b)).multiply(b).abs();
    }

    public static ExtendedGdcBigIntegerResult extendedGcd(BigInteger a, BigInteger b) {
        BigInteger old_r = a.abs();
        BigInteger r = b.abs();
        BigInteger old_s = ONE;
        BigInteger s = ZERO;
        BigInteger old_t = ZERO;
        BigInteger t = ONE;

        while (r.signum() != 0) {
            BigInteger q = old_r.divide(r);

            BigInteger tmp = r;
            r = old_r.subtract(q.multiply(r));
            old_r = tmp;

            tmp = s;
            s = old_s.subtract(q.multiply(s));
            old_s = tmp;

            tmp = t;
            t = old_t.subtract(q.multiply(t));
            old_t = tmp;
        }

        return new ExtendedGdcBigIntegerResult(old_r, old_s, old_t);
    }

    public static boolean isOdd(BigInteger n) {
        return n.testBit(0);
    }

    public static boolean isEven(BigInteger n) {
        return !n.testBit(0);
    }

    public static boolean isProbablePrime(BigInteger n, int certainty) {
        if (certainty <= 0) {
            throw new IllegalArgumentException();
        }

        return n.isProbablePrime(certainty);
    }

    public static boolean isPrime(BigInteger n) {
        if (n.signum() <= 0) {
            throw new IllegalArgumentException();
        } else if (n.equals(ONE)) {
            return false;
        } else if (isEven(n)) {
            return false;
        }

        throw new UnsupportedOperationException();
    }

    public static boolean isPowerOf2(BigInteger n) {
        return n.signum() != 0 && n.and(n.subtract(ONE)).signum() == 0;
    }

    public static boolean isInt(BigInteger n) {
        return n.bitLength() < Integer.SIZE;
    }

    public static boolean isLong(BigInteger n) {
        return n.bitLength() < Long.SIZE;
    }

    public static BigInteger binomial(BigInteger n, BigInteger k) {
        if (k.signum() < 0 || k.compareTo(n) > 0) {
            return ZERO;
        } else if (k.signum() == 0 || n.equals(k)) {
            return ONE;
        }

        k = k.min(n.subtract(k));

        BigInteger result = ONE;
        BigInteger product = ONE;
        for (BigInteger i = ONE; i.compareTo(k) <= 0; i = i.add(ONE)) {
            product = product.multiply(i);
            result = result.multiply(n.subtract(k).add(i));
        }
        return result.divide(product);
    }

    public static BigInteger multinomial(BigInteger... n) {
        BigInteger result = ONE;
        BigInteger sum = ZERO;
        for (BigInteger n_i : n) {
            sum = sum.add(n_i);
            result = result.multiply(binomial(sum, n_i));
        }
        return result;
    }

    public static BigInteger factorial(BigInteger n) {
        if (n.signum() < 0) {
            throw new ArithmeticException();
        } else if (n.signum() == 0 || n.equals(ONE)) {
            return ONE;
        }

        Map.Entry<BigInteger, BigInteger> maxEntry = FACTORIAL_CACHE.lastEntry();
        BigInteger maxN = maxEntry != null ? maxEntry.getKey() : ONE;
        BigInteger result = maxEntry != null ? maxEntry.getValue() : ONE;

        if (maxN.equals(n)) {
            return result;
        } else if (maxN.compareTo(n) > 0) {
            return FACTORIAL_CACHE.get(n);
        }

        while (maxN.compareTo(n) < 0) {
            maxN = maxN.add(ONE);
            result = result.multiply(maxN);
            FACTORIAL_CACHE.put(maxN, result);
        }

        return result;
    }
}
