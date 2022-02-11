package io.github.ititus.commons.data;

import java.util.*;

public final class ObjectUtil {

    public static String deepToString(Object o) {
        if (o == null) {
            return "null";
        } else if (o instanceof DeepToString) {
            return ((DeepToString) o).deepToString();
        } else if (o instanceof Collection<?> c) {
            boolean set = c instanceof Set;
            if (c.isEmpty()) {
                return set ? "{}" : "[]";
            }

            Iterator<?> it = c.iterator();
            StringBuilder b = new StringBuilder().append(set ? '{' : '[');
            while (true) {
                b.append(deepToString(it.next()));
                if (!it.hasNext()) {
                    return b.append(set ? '}' : ']').toString();
                }

                b.append(',').append(' ');
            }
        } else if (o instanceof Map<?, ?> m) {
            if (m.isEmpty()) {
                return "{}";
            }

            Iterator<? extends Map.Entry<?, ?>> it = m.entrySet().iterator();
            StringBuilder b = new StringBuilder().append('{');
            while (true) {
                Map.Entry<?, ?> e = it.next();
                b.append(deepToString(e.getKey())).append('=').append(deepToString(e.getValue()));
                if (!it.hasNext()) {
                    return b.append('}').toString();
                }

                b.append(',').append(' ');
            }
        } else if (o instanceof Optional<?> opt) {
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
        } else if (o instanceof Printable p) {
            return p.toPrintableString();
        } else if (o.getClass().isArray()) {
            return ArrayUtil.toString(o);
        }

        return o.toString();
    }
}
