package io.github.ititus.commons.data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

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

    public static int interpolationSearch(int[] arr, int key) {
        return interpolationSearch(arr, 0, arr.length, key);
    }

    public static int interpolationSearch(int[] arr, int fromIndex, int toIndex, int key) {
        if (fromIndex < 0 || toIndex > arr.length || fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }

        int low = fromIndex;
        int high = toIndex - 1;

        while (arr[high] != arr[low] && key >= arr[low] && key <= arr[high]) {
            int mid = low + ((key - arr[low]) * (high - low) / (arr[high] - arr[low]));

            if (arr[mid] < key) {
                low = mid + 1;
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        if (key == arr[low]) {
            return low;
        } else {
            return -(low + 1);
        }
    }

    public static IntStream stream(char[] array) {
        return stream(array, 0, array.length);
    }

    public static IntStream stream(char[] array, int startInclusive, int endExclusive) {
        return StreamSupport.intStream(spliterator(array, startInclusive, endExclusive), false);
    }

    public static Spliterator.OfInt spliterator(char[] array) {
        return new CharArraySpliterator(Objects.requireNonNull(array), Spliterator.ORDERED | Spliterator.IMMUTABLE);
    }

    public static Spliterator.OfInt spliterator(char[] array, int startInclusive, int endExclusive) {
        return new CharArraySpliterator(Objects.requireNonNull(array), startInclusive, endExclusive, Spliterator.ORDERED | Spliterator.IMMUTABLE);
    }

    private static final class CharArraySpliterator implements Spliterator.OfInt {

        private final char[] array;
        private final int fence;
        private final int characteristics;
        private int index;

        public CharArraySpliterator(char[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

        public CharArraySpliterator(char[] array, int origin, int fence, int additionalCharacteristics) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.characteristics = additionalCharacteristics | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        @Override
        public OfInt trySplit() {
            int lo = index, mid = (lo + fence) >>> 1;
            return lo >= mid ? null : new CharArraySpliterator(array, lo, index = mid, characteristics);
        }

        @Override
        public void forEachRemaining(IntConsumer action) {
            char[] a;
            int i, hi;
            if (action == null) {
                throw new NullPointerException();
            } else if ((a = array).length >= (hi = fence) && (i = index) >= 0 && i < (index = hi)) {
                do
                {
                    action.accept(a[i]);
                } while (++i < hi);
            }
        }

        @Override
        public boolean tryAdvance(IntConsumer action) {
            if (action == null) {
                throw new NullPointerException();
            } else if (index >= 0 && index < fence) {
                action.accept(array[index++]);
                return true;
            }

            return false;
        }

        @Override
        public long estimateSize() {
            return fence - index;
        }

        @Override
        public int characteristics() {
            return characteristics;
        }

        @Override
        public Comparator<? super Integer> getComparator() {
            if (hasCharacteristics(Spliterator.SORTED)) {
                return null;
            }

            throw new IllegalStateException();
        }
    }
}
