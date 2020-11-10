package io.github.ititus.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public final class ObjectUtil {

    public static String deepToString(Object o) {
        if (o == null) {
            return "null";
        } else if (o instanceof Collection) {
            return ((Collection<?>) o).stream().map(ObjectUtil::deepToString).collect(Collectors.joining(", ", "[",
                    "]"));
        } else if (o instanceof Map) {
            return ((Map<?, ?>) o).entrySet().stream().map(e -> deepToString(e.getKey()) + "=" + deepToString(e.getValue())).collect(Collectors.joining(", ", "{", "}"));
        }

        Class<?> c = o.getClass();
        if (!c.isArray()) {
            return o.toString();
        }

        if (c == byte[].class) {
            return Arrays.toString((byte[]) o);
        } else if (c == short[].class) {
            return Arrays.toString((short[]) o);
        } else if (c == int[].class) {
            return Arrays.toString((int[]) o);
        } else if (c == long[].class) {
            return Arrays.toString((long[]) o);
        } else if (c == char[].class) {
            return Arrays.toString((char[]) o);
        } else if (c == float[].class) {
            return Arrays.toString((float[]) o);
        } else if (c == double[].class) {
            return Arrays.toString((double[]) o);
        } else if (c == boolean[].class) {
            return Arrays.toString((boolean[]) o);
        } else { // element is an array of object references
            return Arrays.deepToString((Object[]) o);
        }
    }
}
