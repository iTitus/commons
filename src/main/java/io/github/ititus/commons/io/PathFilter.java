package io.github.ititus.commons.io;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;

@FunctionalInterface
public interface PathFilter extends Predicate<Path> {

    static PathFilter isEqual(Path targetRef) {
        return targetRef == null ? Objects::isNull : targetRef::equals;
    }

    static PathFilter not(Predicate<? super Path> target) {
        Objects.requireNonNull(target);
        return p -> !target.test(p);
    }

    @Override
    default PathFilter and(Predicate<? super Path> filter) {
        Objects.requireNonNull(filter);
        return p -> test(p) && filter.test(p);
    }

    @Override
    default PathFilter negate() {
        return p -> !test(p);
    }

    @Override
    default PathFilter or(Predicate<? super Path> filter) {
        Objects.requireNonNull(filter);
        return p -> test(p) || filter.test(p);
    }

    default PathFilter xor(Predicate<? super Path> filter) {
        Objects.requireNonNull(filter);
        return p -> test(p) ^ filter.test(p);
    }
}
