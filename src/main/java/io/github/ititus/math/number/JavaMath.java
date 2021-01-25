package io.github.ititus.math.number;

import java.math.BigInteger;

import static io.github.ititus.math.number.BigIntegerConstants.ONE;

public final class JavaMath {

    public static final short UNSIGNED_BYTE_MAX_VALUE = (1 << Byte.SIZE) - 1;
    public static final int UNSIGNED_SHORT_MAX_VALUE = (1 << Short.SIZE) - 1;
    public static final long UNSIGNED_INT_MAX_VALUE = (1L << Integer.SIZE) - 1;
    public static final BigInteger UNSIGNED_LONG_MAX_VALUE = ONE.shiftLeft(Long.SIZE).subtract(ONE);

    private JavaMath() {
    }

    public static boolean isInt(double d) {
        return Double.isFinite(d) && Double.compare(d, Math.rint(d)) == 0;
    }

    public static int toInt(double d) {
        return (int) d;
    }

    public static int roundToInt(double d) {
        return Math.toIntExact(Math.round(d));
    }

    public static long roundToLong(double d) {
        return Math.round(d);
    }

    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        if (a == 0) {
            return Math.abs(b);
        } else if (b == 0) {
            return Math.abs(a);
        }

        int i = Integer.numberOfTrailingZeros(a);
        a >>>= i;

        int j = Integer.numberOfTrailingZeros(b);
        b >>>= j;

        int k = Math.min(i, j);

        while (true) {
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            b -= a;

            if (b == 0) {
                return a << k;
            }

            b >>= Integer.numberOfTrailingZeros(b);
        }
    }

    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);

        if (a == 0) {
            return b;
        } else if (b == 0) {
            return a;
        }

        int i = Long.numberOfTrailingZeros(a);
        a >>>= i;

        int j = Long.numberOfTrailingZeros(b);
        b >>>= j;

        int k = Math.min(i, j);

        while (true) {
            if (a > b) {
                long tmp = a;
                a = b;
                b = tmp;
            }

            b -= a;

            if (b == 0) {
                return a << k;
            }

            b >>= Long.numberOfTrailingZeros(b);
        }
    }

    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            throw new ArithmeticException();
        }

        return Math.multiplyExact(Math.abs(a) / gcd(a, b), Math.abs(b));
    }

    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) {
            throw new ArithmeticException();
        }

        return Math.multiplyExact(Math.abs(a) / gcd(a, b), Math.abs(b));
    }

    public static ExtendedGdcIntResult extendedGcd(int a, int b) {
        int old_r = Math.abs(a);
        int r = Math.abs(b);
        int old_s = 1;
        int s = 0;
        int old_t = 0;
        int t = 1;

        while (r != 0) {
            int q = old_r / r;

            int tmp = r;
            r = old_r - (q * r);
            old_r = tmp;

            tmp = s;
            s = old_s - (q * s);
            old_s = tmp;

            tmp = t;
            t = old_t - (q * t);
            old_t = tmp;
        }

        return new ExtendedGdcIntResult(old_r, old_s, old_t);
    }

    public static ExtendedGdcLongResult extendedGcd(long a, long b) {
        long old_r = Math.abs(a);
        long r = Math.abs(b);
        long old_s = 1;
        long s = 0;
        long old_t = 0;
        long t = 1;

        while (r != 0) {
            long q = old_r / r;

            long tmp = r;
            r = old_r - (q * r);
            old_r = tmp;

            tmp = s;
            s = old_s - (q * s);
            old_s = tmp;

            tmp = t;
            t = old_t - (q * t);
            old_t = tmp;
        }

        return new ExtendedGdcLongResult(old_r, old_s, old_t);
    }

    public static int sgn(int n) {
        return n < 0 ? -1 : n > 0 ? 1 : 0;
    }

    public static int sgn(long n) {
        return n < 0 ? -1 : n > 0 ? 1 : 0;
    }

    public static BigInteger toUnsignedBigInteger(long n) {
        return BigIntegerMath.of(n).and(UNSIGNED_LONG_MAX_VALUE);
    }

    public static int ceilDiv(int x, int y) {
        return Math.floorDiv(x, y) + (x % y == 0 ? 0 : 1);
    }

    public static long ceilDiv(long x, int y) {
        return Math.floorDiv(x, y) + (x % y == 0 ? 0 : 1);
    }

    public static long ceilDiv(long x, long y) {
        return Math.floorDiv(x, y) + (x % y == 0 ? 0 : 1);
    }
}
