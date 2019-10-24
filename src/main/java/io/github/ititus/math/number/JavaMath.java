package io.github.ititus.math.number;

public final class JavaMath {

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
        if (a == 0) {
            return Math.abs(b);
        } else if (b == 0) {
            return Math.abs(a);
        }
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            throw new ArithmeticException();
        }
        return Math.multiplyExact(Math.abs(a) / gcd(a, b), Math.abs(b));
    }
}
