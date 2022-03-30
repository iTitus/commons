package io.github.ititus.commons.lexer.token;

import io.github.ititus.commons.data.StreamUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapBasedCharacterTokenType<T> extends MultiCharTokenType<T> {

    private final Map<Character, T> conversions;

    private MapBasedCharacterTokenType(String name, Map<Character, T> conversions) {
        super(name, StreamUtil.toCharArray(conversions.keySet().stream().mapToInt(Character::charValue)));
        this.conversions = conversions;
    }

    public static <T extends Enum<T> & CharacterToken> MapBasedCharacterTokenType<T> of(String name, Class<T> enumClass) {
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException();
        }

        return new MapBasedCharacterTokenType<>(
                name,
                Arrays.stream(enumClass.getEnumConstants())
                        .collect(Collectors.toUnmodifiableMap(CharacterToken::character, Function.identity()))
        );
    }

    public static <T> MapBasedCharacterTokenType<T> of(String name, Map<Character, T> tokens) {
        return new MapBasedCharacterTokenType<>(
                name,
                Map.copyOf(tokens)
        );
    }

    @Override
    public T convert(String token) {
        return conversions.get(token.charAt(0));
    }

    @FunctionalInterface
    public interface CharacterToken {

        char character();

    }
}
