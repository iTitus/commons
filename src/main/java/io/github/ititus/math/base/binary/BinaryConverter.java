package io.github.ititus.math.base.binary;

import io.github.ititus.math.base.BaseConverter;
import io.github.ititus.math.number.BigIntegerMath;

import java.math.BigInteger;
import java.util.BitSet;

public final class BinaryConverter extends BaseConverter {

    public static final BinaryConverter INSTANCE = new BinaryConverter();

    private BinaryConverter() {
        super(2);
    }

    @SuppressWarnings("Duplicates")
    public BinaryNumber encodeToBinaryNumber(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (n == 0) {
            return BinaryNumber.ZERO;
        }

        BitSet bs = new BitSet();
        int i = 0;
        do {
            bs.set(i++, (n & 1) == 1);
            n >>>= 1;
        } while (n > 0);

        return BinaryNumber.of(bs);
    }

    @SuppressWarnings("Duplicates")
    public BinaryNumber encodeToBinaryNumber(long n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (n == 0) {
            return BinaryNumber.ZERO;
        }

        BitSet bs = new BitSet();
        int i = 0;
        do {
            bs.set(i++, (n & 1) == 1);
            n >>>= 1;
        } while (n > 0);

        return BinaryNumber.of(bs);
    }

    public BinaryNumber encodeToBinaryNumber(BigInteger n) {
        if (n == null || n.signum() < 0) {
            throw new IllegalArgumentException();
        } else if (n.signum() == 0) {
            return BinaryNumber.ZERO;
        }

        BitSet bs = new BitSet();
        int i = 0;
        do {
            bs.set(i++, BigIntegerMath.isOdd(n));
            n = n.shiftRight(1);
        } while (n.signum() > 0);

        return BinaryNumber.of(bs);
    }

    public int decodeToInt(BinaryNumber bn) {
        int n = 0;
        int b = 1;

        for (int i = 0; i < bn.getLength(); i++) {
            if (bn.get(i)) {
                if (i >= 31) {
                    throw new ArithmeticException("integer overflow");
                }

                n += b;
            }

            b <<= 1;
        }

        return n;
    }

    public long decodeToLong(BinaryNumber bn) {
        long n = 0;
        long b = 1;

        for (int i = 0; i < bn.getLength(); i++) {
            if (bn.get(i)) {
                if (i >= 63) {
                    throw new ArithmeticException("long overflow");
                }

                n += b;
            }

            b <<= 1;
        }

        return n;
    }

    public BigInteger decodeToBigInteger(BinaryNumber bn) {
        BigInteger n = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;

        for (int i = 0; i < bn.getLength(); i++) {
            if (bn.get(i)) {
                n = n.add(b);
            }

            b = b.shiftLeft(1);
        }

        return n;
    }
}
