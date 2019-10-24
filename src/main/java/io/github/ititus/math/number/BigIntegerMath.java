package io.github.ititus.math.number;

import io.github.ititus.math.time.StopWatchStatistics;

import java.math.BigInteger;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public final class BigIntegerMath {

    public static final BigInteger THREE = of(3);
    public static final BigInteger FOUR = of(4);
    public static final BigInteger FIVE = of(5);
    public static final BigInteger MINUS_ONE = of(-1);

    private static final NavigableMap<BigInteger, BigInteger> FACTORIAL_CACHE = new TreeMap<>();
    private static final StopWatchStatistics gcdTime = StopWatchStatistics.arithmetic();

    private BigIntegerMath() {
    }

    public static BigInteger of(int n) {
        return BigInteger.valueOf(n);
    }

    public static BigInteger of(long n) {
        return BigInteger.valueOf(n);
    }

    public static BigInteger of(String n) {
        return new BigInteger(n);
    }

    @SuppressWarnings("Duplicates")
    public static BigInteger pow(BigInteger base, BigInteger exponent) {
        if (base.equals(BigInteger.ONE) || exponent.signum() == 0) {
            return BigInteger.ONE;
        } else if (base.signum() == 0) {
            return BigInteger.ZERO;
        } else if (exponent.signum() < 0) {
            throw new ArithmeticException();
        }

        BigInteger n = BigInteger.ONE;

        while (exponent.signum() > 0) {
            if (BigIntegerMath.isOdd(exponent)) {
                n = n.multiply(base);
                exponent = exponent.subtract(BigInteger.ONE);
            } else {
                base = base.multiply(base);
                exponent = exponent.shiftRight(1);
            }
        }

        return n;
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        //gcdTime.start();
        BigInteger gcd;
        if (a.signum() == 0) {
            gcd = b.abs();
        } else if (b.signum() == 0 || a.equals(b)) {
            gcd = a.abs();
        } else if (a.equals(BigInteger.ONE) || b.equals(BigInteger.ONE)) {
            gcd = BigInteger.ONE;
        } /*else if (!isProbablePrime(a, 10) && !isProbablePrime(a, 10)) {
            gcd = a.gcd(b);
        }*/ else { // a or b might be prime
            gcd = a.gcd(b);
        }
        //System.out.println("gcd: " + gcdTime.stop());
        return gcd;
    }

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        if (a.equals(BigInteger.ZERO) || b.equals(BigInteger.ZERO)) {
            throw new ArithmeticException();
        }

        return a.divide(gcd(a, b)).multiply(b).abs();
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
        } else if (n.equals(BigInteger.ONE)) {
            return false;
        } else if (isEven(n)) {
            return false;
        }

        throw new UnsupportedOperationException();
    }

    public static boolean isPowerOf2(BigInteger n) {
        return n.signum() != 0 && n.and(n.subtract(BigInteger.ONE)).signum() == 0;
    }

    public static boolean isInt(BigInteger n) {
        return n.bitLength() <= 31;
    }

    public static boolean isLong(BigInteger n) {
        return n.bitLength() <= 63;
    }

    public static BigInteger binomial(BigInteger n, BigInteger k) {
        if (k.signum() < 0 || k.compareTo(n) > 0) {
            return BigInteger.ZERO;
        } else if (k.signum() == 0 || n.equals(k)) {
            return BigInteger.ONE;
        }

        k = k.min(n.subtract(k));

        BigInteger result = BigInteger.ONE;
        BigInteger product = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(k) <= 0; i = i.add(BigInteger.ONE)) {
            product = product.multiply(i);
            result = result.multiply(n.subtract(k).add(i));
        }
        return result.divide(product);
    }

    public static BigInteger multinomial(BigInteger... n) {
        BigInteger result = BigInteger.ONE;
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger n_i : n) {
            sum = sum.add(n_i);
            result = result.multiply(binomial(sum, n_i));
        }
        return result;
    }

    public static BigInteger factorial(BigInteger n) {
        if (n.signum() < 0) {
            throw new ArithmeticException();
        } else if (n.signum() == 0 || n.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        }

        Map.Entry<BigInteger, BigInteger> maxEntry = FACTORIAL_CACHE.lastEntry();
        BigInteger maxN = maxEntry != null ? maxEntry.getKey() : BigInteger.ONE;
        BigInteger result = maxEntry != null ? maxEntry.getValue() : BigInteger.ONE;

        if (maxN.equals(n)) {
            return result;
        } else if (maxN.compareTo(n) > 0) {
            return FACTORIAL_CACHE.get(n);
        }

        while (maxN.compareTo(n) < 0) {
            maxN = maxN.add(BigInteger.ONE);
            result = result.multiply(maxN);
            FACTORIAL_CACHE.put(maxN, result);
        }

        return result;
    }
}
