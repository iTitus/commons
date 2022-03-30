package io.github.ititus.commons.data;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class StreamUtil {

    private StreamUtil() {}

    public static char[] toCharArray(IntStream stream) {
        return stream
                .mapToObj(Character::toString)
                .collect(Collectors.joining())
                .toCharArray();
    }
}
