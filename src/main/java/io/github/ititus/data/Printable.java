package io.github.ititus.data;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public interface Printable extends DeepToString {

    String getPrefix();

    void getPrintableFields(Map<String, ?> fields);

    @SuppressWarnings("Duplicates")
    default String toPrintableString() {
        Map<String, Object> fields = new LinkedHashMap<>();
        getPrintableFields(fields);

        String prefix = getPrefix();
        if (prefix == null) {
            prefix = "";
        }

        Iterator<Map.Entry<String, Object>> it = fields.entrySet().iterator();
        if (!it.hasNext()) {
            return prefix + "{}";
        }

        StringBuilder b = new StringBuilder().append(prefix).append('{');
        while (true) {
            Map.Entry<String, Object> e = it.next();
            b.append(e.getKey()).append('=').append(e.getValue());
            if (!it.hasNext()) {
                return b.append('}').toString();
            }

            b.append(',').append(' ');
        }
    }

    @Override
    @SuppressWarnings("Duplicates")
    default String deepToString() {
        Map<String, Object> fields = new LinkedHashMap<>();
        getPrintableFields(fields);

        String prefix = getPrefix();
        if (prefix == null) {
            prefix = "";
        }

        Iterator<Map.Entry<String, Object>> it = fields.entrySet().iterator();
        if (!it.hasNext()) {
            return prefix + "{}";
        }

        StringBuilder b = new StringBuilder().append(prefix).append('{');
        while (true) {
            Map.Entry<String, Object> e = it.next();
            b.append(e.getKey()).append('=').append(ObjectUtil.deepToString(e.getValue()));
            if (!it.hasNext()) {
                return b.append('}').toString();
            }

            b.append(',').append(' ');
        }
    }
}
