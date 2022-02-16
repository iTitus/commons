package io.github.ititus.commons.lexer.token;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapBasedCharacterTokenType<T> extends MultiCharTokenType<T> {

    private final Map<Integer, T> conversions;

    private MapBasedCharacterTokenType(String name, Map<Integer, T> conversions) {
        super(name, conversions.keySet().stream().mapToInt(Integer::intValue).toArray());
        this.conversions = conversions;
    }

    public static <T extends Enum<T> & CharacterToken> MapBasedCharacterTokenType<T> of(String name, Class<T> enumClass) {
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException();
        }

        return new MapBasedCharacterTokenType<>(
                name,
                Arrays.stream(enumClass.getEnumConstants())
                        .collect(Collectors.toUnmodifiableMap(CharacterToken::codepoint, Function.identity()))
        );
    }

    public static <T> MapBasedCharacterTokenType<T> of(String name, Map<Integer, T> tokens) {
        return new MapBasedCharacterTokenType<>(
                name,
                Map.copyOf(tokens)
        );
    }

    @Override
    public T convert(String token) {
        return conversions.get(token.codePointAt(0));
    }

    @FunctionalInterface
    public interface CharacterToken {

        int codepoint();

    }
}
