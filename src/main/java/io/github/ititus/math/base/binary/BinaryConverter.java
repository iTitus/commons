package io.github.ititus.math.base.binary;

import io.github.ititus.math.base.BaseConverter;

import java.math.BigInteger;
import java.util.BitSet;

public final class BinaryConverter extends BaseConverter {

    public static final BinaryConverter INSTANCE = new BinaryConverter();

    private BinaryConverter() {
        super(2);
    }

    @SuppressWarnings("Duplicates")
    public BinaryNumber encodeToBinaryNumberUnsigned(int n) {
        if (n == 0) {
            return BinaryNumber.ZERO;
        }

        BitSet bs = new BitSet();
        int i = 0;
        do {
            bs.set(i++, (n & 1) != 0);
            n >>>= 1;
        } while (n != 0);

        return BinaryNumber.of(bs);
    }

    @SuppressWarnings("Duplicates")
    public BinaryNumber encodeToBinaryNumberUnsigned(long n) {
        if (n == 0) {
            return BinaryNumber.ZERO;
        }

        BitSet bs = new BitSet();
        int i = 0;
        do {
            bs.set(i++, (n & 1) != 0);
            n >>>= 1;
        } while (n != 0);

        return BinaryNumber.of(bs);
    }

    public BinaryNumber encodeToBinaryNumberUnsigned(BigInteger n) {
        if (n == null) {
            throw new IllegalArgumentException();
        } else if (n.signum() == 0) {
            return BinaryNumber.ZERO;
        }

        BitSet bs = new BitSet();
        int i = 0;
        do {
            bs.set(i++, n.testBit(0));
            n = n.shiftRight(1);
        } while (n.signum() > 0);

        return BinaryNumber.of(bs);
    }

    public int decodeToIntUnsigned(BinaryNumber bn) {
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

    public long decodeToLongUnsigned(BinaryNumber bn) {
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

    public BigInteger decodeToBigIntegerUnsigned(BinaryNumber bn) {
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
