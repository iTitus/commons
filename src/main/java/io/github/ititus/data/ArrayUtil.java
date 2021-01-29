package io.github.ititus.data;

import java.util.Arrays;

public final class ArrayUtil {

    private ArrayUtil() {
    }

    public static boolean[] array(boolean... arr) {
        return arr;
    }

    public static int hash(boolean... arr) {
        return Arrays.hashCode(arr);
    }

    public static byte[] array(byte... arr) {
        return arr;
    }

    public static int hash(byte... arr) {
        return Arrays.hashCode(arr);
    }

    public static char[] array(char... arr) {
        return arr;
    }

    public static int hash(char... arr) {
        return Arrays.hashCode(arr);
    }

    public static short[] array(short... arr) {
        return arr;
    }

    public static int hash(short... arr) {
        return Arrays.hashCode(arr);
    }

    public static int[] array(int... arr) {
        return arr;
    }

    public static int hash(int... arr) {
        return Arrays.hashCode(arr);
    }

    public static long[] array(long... arr) {
        return arr;
    }

    public static int hash(long... arr) {
        return Arrays.hashCode(arr);
    }

    public static float[] array(float... arr) {
        return arr;
    }

    public static int hash(float... arr) {
        return Arrays.hashCode(arr);
    }

    public static double[] array(double... arr) {
        return arr;
    }

    public static int hash(double... arr) {
        return Arrays.hashCode(arr);
    }

    @SuppressWarnings("Duplicates")
    public static String toString(Object arr) {
        if (arr == null) {
            return "null";
        }

        Class<?> clazz = arr.getClass();
        if (!clazz.isArray()) {
            throw new IllegalArgumentException("given object " + arr + " is not an array");
        } else if (clazz == byte[].class) {
            return Arrays.toString((byte[]) arr);
        } else if (clazz == short[].class) {
            return Arrays.toString((short[]) arr);
        } else if (clazz == int[].class) {
            return Arrays.toString((int[]) arr);
        } else if (clazz == long[].class) {
            return Arrays.toString((long[]) arr);
        } else if (clazz == char[].class) {
            return Arrays.toString((char[]) arr);
        } else if (clazz == float[].class) {
            return Arrays.toString((float[]) arr);
        } else if (clazz == double[].class) {
            return Arrays.toString((double[]) arr);
        } else if (clazz == boolean[].class) {
            return Arrays.toString((boolean[]) arr);
        }

        return Arrays.toString((Object[]) arr);
    }

    @SuppressWarnings("Duplicates")
    public static String deepToString(Object arr) {
        if (arr == null) {
            return "null";
        }

        Class<?> clazz = arr.getClass();
        if (!clazz.isArray()) {
            throw new IllegalArgumentException("given object " + arr + " is not an array");
        } else if (clazz == byte[].class) {
            return Arrays.toString((byte[]) arr);
        } else if (clazz == short[].class) {
            return Arrays.toString((short[]) arr);
        } else if (clazz == int[].class) {
            return Arrays.toString((int[]) arr);
        } else if (clazz == long[].class) {
            return Arrays.toString((long[]) arr);
        } else if (clazz == char[].class) {
            return Arrays.toString((char[]) arr);
        } else if (clazz == float[].class) {
            return Arrays.toString((float[]) arr);
        } else if (clazz == double[].class) {
            return Arrays.toString((double[]) arr);
        } else if (clazz == boolean[].class) {
            return Arrays.toString((boolean[]) arr);
        }

        return Arrays.deepToString((Object[]) arr);
    }
}
