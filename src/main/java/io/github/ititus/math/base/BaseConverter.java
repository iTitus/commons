package io.github.ititus.math.base;

import io.github.ititus.math.number.BigIntegerMath;

import java.math.BigInteger;

public class BaseConverter {

    protected static final char[] CHARACTERS;
    private static final byte MIN_BASE = 2;
    private static final byte MAX_BASE = 36;
    private static final BigInteger[] BASES;

    static {
        CHARACTERS = new char[MAX_BASE];
        int i = 0;
        for (char c = '0'; c <= '9'; c++, i++) {
            CHARACTERS[i] = c;
        }
        for (char c = 'a'; c <= 'z'; c++, i++) {
            CHARACTERS[i] = c;
        }

        BASES = new BigInteger[MAX_BASE - MIN_BASE];
        for (int j = 0; j < BASES.length; j++) {
            BASES[j] = BigIntegerMath.of(j + MIN_BASE);
        }
    }

    protected final int base;

    protected BaseConverter(int base) {
        if (base < MIN_BASE || base > MAX_BASE) {
            throw new IllegalArgumentException();
        }
        this.base = base;
    }

    private static int digitToInt(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        } else if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 10;
        }

        throw new IllegalArgumentException("Unrecognized digit '" + c + "'");
    }

    public String encode(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (n == 0) {
            return String.valueOf(CHARACTERS[0]);
        }

        StringBuilder b = new StringBuilder();
        do {
            b.append(CHARACTERS[n % base]);
            n /= base;
        } while (n > 0);

        return b.reverse().toString();
    }

    public String encode(long n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (n == 0) {
            return String.valueOf(CHARACTERS[0]);
        }

        StringBuilder b = new StringBuilder();
        do {
            b.append(CHARACTERS[Math.toIntExact(n % base)]);
            n /= base;
        } while (n > 0);

        return b.reverse().toString();
    }

    public String encode(BigInteger n) {
        if (n == null || n.signum() < 0) {
            throw new IllegalArgumentException();
        } else if (n.signum() == 0) {
            return String.valueOf(CHARACTERS[0]);
        }

        StringBuilder sb = new StringBuilder();
        BigInteger b = BASES[base];
        do {
            BigInteger[] dr = n.divideAndRemainder(b);
            sb.append(CHARACTERS[dr[1].intValueExact()]);
            n = dr[0];
        } while (n.signum() > 0);

        return sb.reverse().toString();
    }

    @SuppressWarnings("Duplicates")
    public int decodeToInt(String s) {
        char[] chars = s.toCharArray();
        int n = 0;
        int b = 1;

        for (int i = chars.length - 1; i >= 0; i--) {
            int d = digitToInt(chars[i]);
            if (d > 0) {
                if (b < 0) {
                    throw new ArithmeticException("integer overflow");
                }

                n = Math.addExact(n, Math.multiplyExact(b, d));
            }

            if (b > 0) {
                b *= base;
            }
        }

        return n;
    }

    @SuppressWarnings("Duplicates")
    public long decodeToLong(String s) {
        char[] chars = s.toCharArray();
        long n = 0;
        long b = 1;

        for (int i = chars.length - 1; i >= 0; i--) {
            int d = digitToInt(chars[i]);
            if (d > 0) {
                if (b < 0) {
                    throw new ArithmeticException("long overflow");
                }

                n = Math.addExact(n, Math.multiplyExact(b, d));
            }

            if (b > 0) {
                b *= base;
            }
        }

        return n;
    }

    public BigInteger decodeToBigInteger(String s) {
        char[] chars = s.toCharArray();
        BigInteger n = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;

        for (int i = chars.length - 1; i >= 0; i--) {
            n = n.add(b.multiply(BigIntegerMath.of(digitToInt(chars[i]))));
            b = b.multiply(BASES[base]);
        }

        return n;
    }
}
