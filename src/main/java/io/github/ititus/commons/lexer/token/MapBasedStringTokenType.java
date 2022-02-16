package io.github.ititus.commons.lexer.token;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapBasedStringTokenType<T> extends MultiStringTokenType<T> {

    private final Map<String, T> conversions;

    private MapBasedStringTokenType(String name, Map<String, T> conversions) {
        super(name, List.copyOf(conversions.keySet()));
        this.conversions = conversions;
    }

    public static <T extends Enum<T>> MapBasedStringTokenType<T> of(String name, Class<T> enumClass) {
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException();
        }

        return new MapBasedStringTokenType<>(
                name,
                Arrays.stream(enumClass.getEnumConstants())
                        .collect(Collectors.toUnmodifiableMap(Object::toString, Function.identity()))
        );
    }

    public static <T> MapBasedStringTokenType<T> of(String name, Map<String, T> tokens) {
        return new MapBasedStringTokenType<>(
                name,
                Map.copyOf(tokens)
        );
    }

    @Override
    public T convert(String token) {
        return conversions.get(token);
    }
}
