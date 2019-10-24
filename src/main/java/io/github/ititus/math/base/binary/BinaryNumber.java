package io.github.ititus.math.base.binary;

import java.util.Arrays;
import java.util.BitSet;

public final class BinaryNumber {

    public static final BinaryNumber ZERO = of(false);
    public static final BinaryNumber ONE = of(true);

    private final boolean[] bits;

    private BinaryNumber(boolean[] bits) {
        this.bits = bits;
    }

    public static BinaryNumber of(boolean... bits) {
        return new BinaryNumber(Arrays.copyOf(bits, bits.length));
    }

    public static BinaryNumber of(String s) {
        BitSet bs = new BitSet(s.length());
        int i = 0;
        for (int j = s.length() - 1; j >= 0; j--) {
            switch (s.charAt(j)) {
                case ' ':
                case '_':
                    break;
                case '1':
                    bs.set(i);
                case '0':
                    i++;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        return of(bs);
    }

    public static BinaryNumber of(int n) {
        return of(n, 32);
    }

    public static BinaryNumber of(int n, int nBits) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        boolean[] bits = new boolean[nBits];
        for (int i = 0; i < bits.length; i++) {
            bits[i] = (n & 1) == 1;
            n >>= 1;
        }

        if (n != 0) {
            throw new ArithmeticException();
        }

        return of(bits);
    }

    public static BinaryNumber of(BitSet bs) {
        boolean[] bits = new boolean[bs.length()];
        for (int i = 0; i < bits.length; i++) {
            bits[i] = bs.get(i);
        }
        return of(bits);
    }

    public static BinaryNumber withSize(int nBits) {
        return of(new boolean[nBits]);
    }

    public static BinaryNumber withSize(int nBits, boolean initial) {
        boolean[] bits = new boolean[nBits];
        Arrays.fill(bits, initial);
        return of(bits);
    }

    public boolean get(int i) {
        if (i < 0 || i >= bits.length) {
            throw new IllegalArgumentException();
        }
        return bits[i];
    }

    public int getAsInt(int i) {
        return get(i) ? 1 : 0;
    }

    public int getLength() {
        return bits.length;
    }

    public BinaryNumber invert() {
        boolean[] newBits = Arrays.copyOf(bits, bits.length);

        for (int i = 0; i < newBits.length; i++) {
            newBits[i] = !newBits[i];
        }

        return new BinaryNumber(newBits);
    }

    public BinaryNumber and(BinaryNumber bn) {
        boolean[] max = bits.length < bn.bits.length ? bn.bits : bits;
        boolean[] min = bits.length > bn.bits.length ? bn.bits : bits;
        boolean[] newBits = new boolean[max.length];

        for (int i = 0; i < newBits.length; i++) {
            newBits[i] = (i < min.length && min[i]) & max[i];
        }

        return of(newBits);
    }

    public BinaryNumber or(BinaryNumber bn) {
        boolean[] max = bits.length < bn.bits.length ? bn.bits : bits;
        boolean[] min = bits.length > bn.bits.length ? bn.bits : bits;
        boolean[] newBits = new boolean[max.length];

        for (int i = 0; i < newBits.length; i++) {
            newBits[i] = (i < min.length && min[i]) | max[i];
        }

        return of(newBits);
    }

    public BinaryNumber xor(BinaryNumber bn) {
        boolean[] max = bits.length < bn.bits.length ? bn.bits : bits;
        boolean[] min = bits.length > bn.bits.length ? bn.bits : bits;
        boolean[] newBits = new boolean[max.length];

        for (int i = 0; i < newBits.length; i++) {
            newBits[i] = (i < min.length && min[i]) ^ max[i];
        }

        return of(newBits);
    }

    public BinaryNumber addWithCarry(BinaryNumber bn, boolean carry) {
        boolean[] max = bits.length < bn.bits.length ? bn.bits : bits;
        boolean[] min = bits.length > bn.bits.length ? bn.bits : bits;
        boolean[] newBits = new boolean[max.length + 1];

        for (int i = 0; i < max.length; i++) {
            boolean xor = (i < min.length && min[i]) ^ max[i];
            newBits[i] = xor ^ carry;
            carry = (carry & xor) | ((i < min.length && min[i]) & max[i]);
        }
        newBits[newBits.length - 1] = carry;

        return of(newBits);
    }

    public BinaryNumber addWithCarry(BinaryNumber bn) {
        return addWithCarry(bn, false);
    }

    public BinaryNumber add(BinaryNumber bn) {
        BinaryNumber withCarry = addWithCarry(bn);

        if (withCarry.get(withCarry.getLength() - 1)) {
            throw new ArithmeticException();
        }

        return withCarry.withLength(withCarry.getLength() - 1);
    }

    public BinaryNumber incrementWithCarry() {
        return addWithCarry(ONE);
    }

    public BinaryNumber increment() {
        return add(ONE);
    }

    public BinaryNumber shiftLeft(int n) {
        if (n < 0) {
            return shiftRight(-n);
        } else if (n == 0) {
            return this;
        }

        boolean[] newBits = new boolean[bits.length + n];
        System.arraycopy(bits, 0, newBits, n, bits.length);
        return of(newBits);
    }

    public BinaryNumber shiftRight(int n) {
        if (n < 0) {
            return shiftLeft(-n);
        } else if (n == 0) {
            return this;
        } else if (n >= bits.length) {
            return ZERO;
        }

        boolean[] newBits = new boolean[bits.length - n];
        System.arraycopy(bits, n, newBits, 0, newBits.length);
        return of(newBits);
    }

    public BinaryNumber withLength(int nBits) {
        if (nBits <= 0) {
            throw new IllegalArgumentException();
        } else if (nBits == bits.length) {
            return this;
        }

        return new BinaryNumber(Arrays.copyOf(bits, nBits));
    }

    public BinaryNumber withLengthExact(int nBits) {
        BinaryNumber bn = withLength(nBits);
        if (nBits < bits.length && !equals(bn)) {
            throw new ArithmeticException("truncating binary number");
        }
        return bn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinaryNumber that = (BinaryNumber) o;
        for (int i = 0; i < Math.max(bits.length, that.bits.length); i++) {
            boolean b = i < bits.length && bits[i];
            if ((i < that.bits.length && that.bits[i] != b) || (i >= that.bits.length && b)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        boolean started = false;
        int result = 1;

        for (int i = bits.length - 1; i >= 0; i--) {
            boolean b = bits[i];
            if (!started && b) {
                started = true;
            }

            if (started) {
                result = 31 * result + Boolean.hashCode(b);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(bits.length);
        for (int i = bits.length - 1; i >= 0; i--) {
            b.append(getAsInt(i));
        }
        return b.toString();
    }
}
