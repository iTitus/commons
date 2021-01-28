package io.github.ititus.data;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public final class ObjectUtil {

    public static String deepToString(Object o) {
        if (o == null) {
            return "null";
        } else if (o instanceof Collection) {
            return ((Collection<?>) o).stream()
                    .map(ObjectUtil::deepToString)
                    .collect(Collectors.joining(", ", "[", "]"));
        } else if (o instanceof Map) {
            return ((Map<?, ?>) o).entrySet().stream()
                    .map(e -> deepToString(e.getKey()) + "=" + deepToString(e.getValue()))
                    .collect(Collectors.joining(", ", "{", "}"));
        }

        if (o.getClass().isArray()) {
            return ArrayUtil.deepToString(o);
        }

        return o.toString();
    }

    public static String toString(Object o) {
        if (o == null) {
            return "null";
        }

        if (o.getClass().isArray()) {
            return ArrayUtil.toString(o);
        }

        return o.toString();
    }
}
