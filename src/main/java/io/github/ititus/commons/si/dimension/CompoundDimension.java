package io.github.ititus.commons.si.dimension;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public final class CompoundDimension extends AbstractDimension {

    private final Map<BaseDimension, Integer> baseDimensions;

    private CompoundDimension(Map<BaseDimension, Integer> baseDimensions) {
        this.baseDimensions = baseDimensions;
    }

    static Dimension of(Map<BaseDimension, Integer> baseDimensions) {
        @SuppressWarnings("unchecked")
        Map.Entry<BaseDimension, Integer>[] entries = baseDimensions.entrySet().stream()
                .filter(e -> e.getValue() != 0)
                .toArray(Map.Entry[]::new);

        if (entries.length == 0) {
            return NONE;
        } else if (entries.length == 1) {
            Map.Entry<BaseDimension, Integer> entry = entries[0];
            if (entry.getValue() == 1) {
                return entry.getKey();
            }

            return new CompoundDimension(Map.of(entry.getKey(), entry.getValue()));
        }

        Map<BaseDimension, Integer> baseDimensions_ = new EnumMap<>(BaseDimension.class);
        for (Map.Entry<BaseDimension, Integer> entry : entries) {
            baseDimensions_.put(entry.getKey(), entry.getValue());
        }

        return new CompoundDimension(Collections.unmodifiableMap(baseDimensions_));
    }

    static Dimension multiply(Map<BaseDimension, Integer> baseDimensions1, Map<BaseDimension, Integer> baseDimensions2) {
        Map<BaseDimension, Integer> baseDimensions = new EnumMap<>(BaseDimension.class);
        baseDimensions.putAll(baseDimensions1);
        baseDimensions2.forEach((dim_, n) -> baseDimensions.merge(dim_, n, Integer::sum));
        return of(baseDimensions);
    }

    static Dimension inverse(Map<BaseDimension, Integer> baseDimensions) {
        Map<BaseDimension, Integer> baseDimensions_ = new EnumMap<>(BaseDimension.class);
        baseDimensions.forEach((dim, n) -> baseDimensions_.put(dim, -n));
        return of(baseDimensions_);
    }

    static Dimension divide(Map<BaseDimension, Integer> baseDimensions1, Map<BaseDimension, Integer> baseDimensions2) {
        Map<BaseDimension, Integer> baseDimensions = new EnumMap<>(BaseDimension.class);
        baseDimensions.putAll(baseDimensions1);
        baseDimensions2.forEach((dim_, n) -> baseDimensions.merge(dim_, -n, (oldValue, newValue) -> oldValue - newValue));
        return of(baseDimensions);
    }

    static Dimension pow(Map<BaseDimension, Integer> baseDimensions, int n) {
        if (n == 0) {
            return NONE;
        } else if (n == 1) {
            return of(baseDimensions);
        } else if (n == -1) {
            return inverse(baseDimensions);
        }

        Map<BaseDimension, Integer> baseDimensions_ = new EnumMap<>(BaseDimension.class);
        baseDimensions.forEach((dim, n_) -> baseDimensions_.put(dim, n * n_));
        return of(baseDimensions_);
    }

    static Dimension root(Map<BaseDimension, Integer> baseDimensions, int n) {
        if (n == 0) {
            throw new ArithmeticException();
        } else if (n == 1) {
            return of(baseDimensions);
        } else if (n == -1) {
            return inverse(baseDimensions);
        }

        Map<BaseDimension, Integer> baseDimensions_ = new EnumMap<>(BaseDimension.class);
        baseDimensions.forEach((dim, n_) -> {
            if (n_ % n != 0) {
                throw new ArithmeticException("fractional powers not supported");
            }

            baseDimensions_.put(dim, n_ / n);
        });
        return of(baseDimensions_);
    }

    @Override
    public String getString() {
        StringBuilder b = new StringBuilder().append('[');

        baseDimensions.forEach((dim, n) -> {
            b.append(dim.getSymbol());

            if (n != 1) {
                b.append('^').append(n);
            }
        });

        return b.append(']').toString();
    }

    @Override
    public Map<BaseDimension, Integer> getBaseDimensions() {
        return baseDimensions;
    }
}
