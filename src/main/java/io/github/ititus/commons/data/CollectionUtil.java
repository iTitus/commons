package io.github.ititus.commons.data;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CollectionUtil {

    private CollectionUtil() {
    }

    public static <T, R> List<R> deepMap(List<? extends T> list, Function<? super T, ? extends R> fct) {
        return list.stream().map(fct).collect(Collectors.toList());
    }

    @SafeVarargs
    private static <T extends Enum<T>> EnumSet<T> toEnumSet(T... ts) {
        return toEnumSet(Arrays.asList(ts));
    }

    private static <T extends Enum<T>> EnumSet<T> toEnumSet(Collection<T> ts) {
        return EnumSet.copyOf(ts);
    }

    @SafeVarargs
    private static <T extends Enum<T>> Set<T> toUnmodifiableEnumSet(T... ts) {
        return toUnmodifiableEnumSet(Arrays.asList(ts));
    }

    private static <T extends Enum<T>> Set<T> toUnmodifiableEnumSet(Collection<T> ts) {
        if (ts.isEmpty()) {
            return Set.of();
        }

        return Collections.unmodifiableSet(EnumSet.copyOf(ts));
    }
}
