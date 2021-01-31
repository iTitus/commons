package io.github.ititus.math.number;

import io.github.ititus.data.ObjectUtil;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class JavaMath {

    public static final short UNSIGNED_BYTE_MAX_VALUE = (1 << Byte.SIZE) - 1;
    public static final int UNSIGNED_SHORT_MAX_VALUE = (1 << Short.SIZE) - 1;
    public static final long UNSIGNED_INT_MAX_VALUE = (1L << Integer.SIZE) - 1;
    public static final BigInteger UNSIGNED_LONG_MAX_VALUE = BigIntegerConstants.UNSIGNED_LONG_MAX_VALUE;

    private JavaMath() {
    }

    public static byte toByte(Object o) {
        Objects.requireNonNull(o);
        if (o instanceof Number) {
            return ((Number) o).byteValue();
        } else if (o instanceof String) {
            return Byte.parseByte((String) o);
        } else if (o instanceof Iterable) {
            Iterator<?> it = ((Iterable<?>) o).iterator();
            if (it.hasNext()) {
                Object n = it.next();
                if (!it.hasNext()) {
                    try {
                        return toByte(n);
                    } catch (RuntimeException e) {
                        throw new IllegalArgumentException(
                                ObjectUtil.toString(o) + " cannot be converted to byte", e);
                    }
                }
            }
        } else if (o.getClass().isArray()) {
            if (Array.getLength(o) == 1) {
                try {
                    return toByte(Array.get(o, 0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to byte", e);
                }
            }
        } else if (o instanceof AtomicIntegerArray) {
            AtomicIntegerArray arr = (AtomicIntegerArray) o;
            if (arr.length() == 1) {
                return (byte) arr.get(0);
            }
        } else if (o instanceof AtomicLongArray) {
            AtomicLongArray arr = (AtomicLongArray) o;
            if (arr.length() == 1) {
                return (byte) arr.get(0);
            }
        } else if (o instanceof AtomicReferenceArray) {
            AtomicReferenceArray<?> arr = (AtomicReferenceArray<?>) o;
            if (arr.length() == 1) {
                try {
                    return toByte(arr.get(0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to byte", e);
                }
            }
        }

        throw new IllegalArgumentException(ObjectUtil.toString(o) + " cannot be converted to byte");
    }

    public static short toShort(Object o) {
        Objects.requireNonNull(o);
        if (o instanceof Number) {
            return ((Number) o).shortValue();
        } else if (o instanceof String) {
            return Short.parseShort((String) o);
        } else if (o instanceof Iterable) {
            Iterator<?> it = ((Iterable<?>) o).iterator();
            if (it.hasNext()) {
                Object n = it.next();
                if (!it.hasNext()) {
                    try {
                        return toShort(n);
                    } catch (RuntimeException e) {
                        throw new IllegalArgumentException(
                                ObjectUtil.toString(o) + " cannot be converted to short", e);
                    }
                }
            }
        } else if (o.getClass().isArray()) {
            if (Array.getLength(o) == 1) {
                try {
                    return toShort(Array.get(o, 0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to short", e);
                }
            }
        } else if (o instanceof AtomicIntegerArray) {
            AtomicIntegerArray arr = (AtomicIntegerArray) o;
            if (arr.length() == 1) {
                return (short) arr.get(0);
            }
        } else if (o instanceof AtomicLongArray) {
            AtomicLongArray arr = (AtomicLongArray) o;
            if (arr.length() == 1) {
                return (short) arr.get(0);
            }
        } else if (o instanceof AtomicReferenceArray) {
            AtomicReferenceArray<?> arr = (AtomicReferenceArray<?>) o;
            if (arr.length() == 1) {
                try {
                    return toShort(arr.get(0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to short", e);
                }
            }
        }

        throw new IllegalArgumentException(ObjectUtil.toString(o) + " cannot be converted to short");
    }

    public static int toInt(Object o) {
        Objects.requireNonNull(o);
        if (o instanceof Number) {
            return ((Number) o).intValue();
        } else if (o instanceof String) {
            return Integer.parseInt((String) o);
        } else if (o instanceof Iterable) {
            Iterator<?> it = ((Iterable<?>) o).iterator();
            if (it.hasNext()) {
                Object n = it.next();
                if (!it.hasNext()) {
                    try {
                        return toInt(n);
                    } catch (RuntimeException e) {
                        throw new IllegalArgumentException(
                                ObjectUtil.toString(o) + " cannot be converted to int", e);
                    }
                }
            }
        } else if (o.getClass().isArray()) {
            if (Array.getLength(o) == 1) {
                try {
                    return toInt(Array.get(o, 0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to int", e);
                }
            }
        } else if (o instanceof AtomicIntegerArray) {
            AtomicIntegerArray arr = (AtomicIntegerArray) o;
            if (arr.length() == 1) {
                return arr.get(0);
            }
        } else if (o instanceof AtomicLongArray) {
            AtomicLongArray arr = (AtomicLongArray) o;
            if (arr.length() == 1) {
                return (int) arr.get(0);
            }
        } else if (o instanceof AtomicReferenceArray) {
            AtomicReferenceArray<?> arr = (AtomicReferenceArray<?>) o;
            if (arr.length() == 1) {
                try {
                    return toInt(arr.get(0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to int", e);
                }
            }
        }

        throw new IllegalArgumentException(ObjectUtil.toString(o) + " cannot be converted to int");
    }

    public static long toLong(Object o) {
        Objects.requireNonNull(o);
        if (o instanceof Number) {
            return ((Number) o).longValue();
        } else if (o instanceof String) {
            return Long.parseLong((String) o);
        } else if (o instanceof Iterable) {
            Iterator<?> it = ((Iterable<?>) o).iterator();
            if (it.hasNext()) {
                Object n = it.next();
                if (!it.hasNext()) {
                    try {
                        return toLong(n);
                    } catch (RuntimeException e) {
                        throw new IllegalArgumentException(
                                ObjectUtil.toString(o) + " cannot be converted to long", e);
                    }
                }
            }
        } else if (o.getClass().isArray()) {
            if (Array.getLength(o) == 1) {
                try {
                    return toLong(Array.get(o, 0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to long", e);
                }
            }
        } else if (o instanceof AtomicIntegerArray) {
            AtomicIntegerArray arr = (AtomicIntegerArray) o;
            if (arr.length() == 1) {
                return arr.get(0);
            }
        } else if (o instanceof AtomicLongArray) {
            AtomicLongArray arr = (AtomicLongArray) o;
            if (arr.length() == 1) {
                return arr.get(0);
            }
        } else if (o instanceof AtomicReferenceArray) {
            AtomicReferenceArray<?> arr = (AtomicReferenceArray<?>) o;
            if (arr.length() == 1) {
                try {
                    return toLong(arr.get(0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to long", e);
                }
            }
        }

        throw new IllegalArgumentException(ObjectUtil.toString(o) + " cannot be converted to long");
    }

    public static float toFloat(Object o) {
        Objects.requireNonNull(o);
        if (o instanceof Number) {
            return ((Number) o).floatValue();
        } else if (o instanceof String) {
            return Float.parseFloat((String) o);
        } else if (o instanceof Iterable) {
            Iterator<?> it = ((Iterable<?>) o).iterator();
            if (it.hasNext()) {
                Object n = it.next();
                if (!it.hasNext()) {
                    try {
                        return toFloat(n);
                    } catch (RuntimeException e) {
                        throw new IllegalArgumentException(
                                ObjectUtil.toString(o) + " cannot be converted to float", e);
                    }
                }
            }
        } else if (o.getClass().isArray()) {
            if (Array.getLength(o) == 1) {
                try {
                    return toFloat(Array.get(o, 0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to float", e);
                }
            }
        } else if (o instanceof AtomicIntegerArray) {
            AtomicIntegerArray arr = (AtomicIntegerArray) o;
            if (arr.length() == 1) {
                return arr.get(0);
            }
        } else if (o instanceof AtomicLongArray) {
            AtomicLongArray arr = (AtomicLongArray) o;
            if (arr.length() == 1) {
                return arr.get(0);
            }
        } else if (o instanceof AtomicReferenceArray) {
            AtomicReferenceArray<?> arr = (AtomicReferenceArray<?>) o;
            if (arr.length() == 1) {
                try {
                    return toFloat(arr.get(0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to float", e);
                }
            }
        }

        throw new IllegalArgumentException(ObjectUtil.toString(o) + " cannot be converted to float");
    }

    public static double toDouble(Object o) {
        Objects.requireNonNull(o);
        if (o instanceof Number) {
            return ((Number) o).doubleValue();
        } else if (o instanceof String) {
            return Double.parseDouble((String) o);
        } else if (o instanceof Iterable) {
            Iterator<?> it = ((Iterable<?>) o).iterator();
            if (it.hasNext()) {
                Object n = it.next();
                if (!it.hasNext()) {
                    try {
                        return toDouble(n);
                    } catch (RuntimeException e) {
                        throw new IllegalArgumentException(
                                ObjectUtil.toString(o) + " cannot be converted to double", e);
                    }
                }
            }
        } else if (o.getClass().isArray()) {
            if (Array.getLength(o) == 1) {
                try {
                    return toDouble(Array.get(o, 0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to double", e);
                }
            }
        } else if (o instanceof AtomicIntegerArray) {
            AtomicIntegerArray arr = (AtomicIntegerArray) o;
            if (arr.length() == 1) {
                return arr.get(0);
            }
        } else if (o instanceof AtomicLongArray) {
            AtomicLongArray arr = (AtomicLongArray) o;
            if (arr.length() == 1) {
                return arr.get(0);
            }
        } else if (o instanceof AtomicReferenceArray) {
            AtomicReferenceArray<?> arr = (AtomicReferenceArray<?>) o;
            if (arr.length() == 1) {
                try {
                    return toDouble(arr.get(0));
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(
                            ObjectUtil.toString(o) + " cannot be converted to double", e);
                }
            }
        }

        throw new IllegalArgumentException(ObjectUtil.toString(o) + " cannot be converted to double");
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

        a = Math.abs(a);
        b = Math.abs(b);

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
        if (a == 0) {
            return Math.abs(b);
        } else if (b == 0) {
            return Math.abs(a);
        }

        a = Math.abs(a);
        b = Math.abs(b);

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
