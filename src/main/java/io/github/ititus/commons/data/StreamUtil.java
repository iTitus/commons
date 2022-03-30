package io.github.ititus.commons.data;

import io.github.ititus.commons.data.array.GrowableCharArray;

import java.util.stream.IntStream;

public final class StreamUtil {

    private StreamUtil() {}

    public static char[] toCharArray(IntStream stream) {
        GrowableCharArray arr = new GrowableCharArray();
        stream.forEachOrdered(c -> arr.append((char) c));
        return arr.toArray();
    }
}
