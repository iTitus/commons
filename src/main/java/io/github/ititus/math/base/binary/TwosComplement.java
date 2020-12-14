package io.github.ititus.math.base.binary;

import io.github.ititus.math.base.BaseConverters;

import java.math.BigInteger;

public final class TwosComplement {

    public static final BinaryNumber MIN_INT = BinaryNumber.ONE.shiftLeft(31);
    public static final BinaryNumber MAX_INT = BinaryNumber.withSize(31, true).withLengthExact(32);
    public static final BinaryNumber MIN_LONG = BinaryNumber.ONE.shiftLeft(63);
    public static final BinaryNumber MAX_LONG = BinaryNumber.withSize(63, true).withLengthExact(64);

    private final int nBits;

    public TwosComplement(int nBits) {
        if (nBits < 1) {
            throw new IllegalArgumentException();
        }

        this.nBits = nBits;
    }

    @SuppressWarnings("Duplicates")
    public int decodeToInt(BinaryNumber bn) {
        if (bn.getLength() > nBits) {
            throw new IllegalArgumentException();
        }

        boolean neg = nBits == bn.getLength() && bn.get(nBits - 1);
        if (neg) {
            bn = bn.invert().increment();
            if (bn.equals(MIN_INT)) {
                return Integer.MIN_VALUE;
            }
        }

        int n = BaseConverters.BINARY.decodeToIntUnsigned(bn);
        return neg ? -n : n;
    }

    @SuppressWarnings("Duplicates")
    public long decodeToLong(BinaryNumber bn) {
        if (bn.getLength() > nBits) {
            throw new IllegalArgumentException();
        }

        boolean neg = nBits == bn.getLength() && bn.get(nBits - 1);
        if (neg) {
            bn = bn.invert().increment();
            if (bn.equals(MIN_LONG)) {
                return Long.MIN_VALUE;
            }
        }

        long n = BaseConverters.BINARY.decodeToLongUnsigned(bn);
        return neg ? -n : n;
    }

    @SuppressWarnings("Duplicates")
    public BigInteger decodeToBigInteger(BinaryNumber bn) {
        if (bn.getLength() > nBits) {
            throw new IllegalArgumentException();
        }

        boolean neg = nBits == bn.getLength() && bn.get(nBits - 1);
        if (neg) {
            bn = bn.invert().increment();
        }

        BigInteger n = BaseConverters.BINARY.decodeToBigIntegerUnsigned(bn);
        return neg ? n.negate() : n;
    }

    @SuppressWarnings("Duplicates")
    public BinaryNumber encode(int n) {
        if (n == Integer.MIN_VALUE) {
            if (nBits < 32) {
                throw new ArithmeticException("integer overflow");
            } else {
                return BinaryNumber.withSize(nBits - 31, true).shiftLeft(31);
            }
        }

        boolean neg = n < 0;
        BinaryNumber bn = BaseConverters.BINARY.encodeToBinaryNumberUnsigned(neg ? Math.negateExact(n) : n);

        if (bn.getLength() >= nBits) {
            throw new ArithmeticException();
        }

        bn = bn.withLengthExact(nBits);
        return neg ? bn.invert().increment() : bn;
    }

    @SuppressWarnings("Duplicates")
    public BinaryNumber encode(long n) {
        if (n == Long.MIN_VALUE) {
            if (nBits < 64) {
                throw new ArithmeticException("long overflow");
            } else {
                return BinaryNumber.withSize(nBits - 63, true).shiftLeft(63);
            }
        }

        boolean neg = n < 0;
        BinaryNumber bn = BaseConverters.BINARY.encodeToBinaryNumberUnsigned(neg ? Math.negateExact(n) : n);

        if (bn.getLength() >= nBits) {
            throw new ArithmeticException();
        }

        bn = bn.withLengthExact(nBits);
        return neg ? bn.invert().increment() : bn;
    }

    public BinaryNumber encode(BigInteger n) {
        BinaryNumber bn = BaseConverters.BINARY.encodeToBinaryNumberUnsigned(n.abs());

        if (bn.getLength() >= nBits) {
            throw new ArithmeticException();
        }

        bn = bn.withLengthExact(nBits);
        return n.signum() < 0 ? bn.invert().increment() : bn;
    }
}
