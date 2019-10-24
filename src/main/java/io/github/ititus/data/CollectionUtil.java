package io.github.ititus.data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CollectionUtil {

    private CollectionUtil() {
    }

    public static <T, R> List<R> deepMap(List<? extends T> list, Function<? super T, ? extends R> fct) {
        return list.stream().map(fct).collect(Collectors.toList());
    }
}
