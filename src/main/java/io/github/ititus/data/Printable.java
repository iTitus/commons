package io.github.ititus.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public interface Printable {

    static String toPrintableString(Printable printable) {
        Map<String, String> fields = new LinkedHashMap<>();
        printable.getPrintableFields(fields);
        return fields.entrySet().stream().map(e -> e.getKey() + '=' + e.getValue()).collect(Collectors.joining(", ", printable.getPrefix() + "{", "}"));
    }

    String getPrefix();

    void getPrintableFields(Map<String, String> fields);
}
