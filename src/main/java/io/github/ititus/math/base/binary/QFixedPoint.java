package io.github.ititus.math.base.binary;

import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;

import java.math.BigInteger;

public final class QFixedPoint {

    private final TwosComplement complement;
    private final BigRational scale;

    public QFixedPoint(int fractionalBits) {
        this(0, fractionalBits);
    }

    public QFixedPoint(int integerBits, int fractionalBits) {
        if (integerBits < 0 || fractionalBits < 0) {
            throw new IllegalArgumentException();
        }

        this.complement = new TwosComplement(1 + integerBits + fractionalBits);
        this.scale = BigRationalConstants.TWO.pow(fractionalBits);
    }

    public BinaryNumber encode(BigRational rational) {
        BigRational scaled = rational.multiply(scale);
        BigInteger intPart = scaled.toBigInteger();

        if (!scaled.subtract(BigRational.of(intPart)).isZero()) {
            throw new ArithmeticException();
        }

        return complement.encode(intPart.intValueExact());
    }

    public BigRational decode(BinaryNumber bn) {
        return BigRational.of(complement.decodeToInt(bn)).divide(scale);
    }
}
