package io.github.ititus.data;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public final class ObjectUtil {

    public static String deepToString(Object o) {
        if (o == null) {
            return "null";
        } else if (o instanceof DeepToString) {
            return ((DeepToString) o).deepToString();
        } else if (o instanceof Iterable) {
            Iterator<?> it = ((Iterable<?>) o).iterator();
            if (!it.hasNext()) {
                return "[]";
            }

            StringBuilder b = new StringBuilder().append('[');
            while (true) {
                b.append(deepToString(it.next()));
                if (!it.hasNext()) {
                    return b.append(']').toString();
                }

                b.append(',').append(' ');
            }
        } else if (o instanceof Map) {
            Iterator<? extends Map.Entry<?, ?>> it = ((Map<?, ?>) o).entrySet().iterator();
            if (!it.hasNext()) {
                return "{}";
            }

            StringBuilder b = new StringBuilder().append('{');
            while (true) {
                Map.Entry<?, ?> e = it.next();
                b.append(deepToString(e.getKey())).append('=').append(deepToString(e.getValue()));
                if (!it.hasNext()) {
                    return b.append('}').toString();
                }

                b.append(',').append(' ');
            }
        } else if (o instanceof Optional) {
            Optional<?> opt = (Optional<?>) o;
            if (opt.isEmpty()) {
                return "Optional.empty";
            }

            return "Optional[" + deepToString(opt.get()) + "]";
        } else if (o.getClass().isArray()) {
            return ArrayUtil.deepToString(o);
        }

        return o.toString();
    }

    public static String toString(Object o) {
        if (o == null) {
            return "null";
        } else if (o instanceof Printable) {
            return ((Printable) o).toPrintableString();
        } else if (o.getClass().isArray()) {
            return ArrayUtil.toString(o);
        }

        return o.toString();
    }
}
