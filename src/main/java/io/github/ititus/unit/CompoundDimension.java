package io.github.ititus.unit;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public final class CompoundDimension implements Dimension {

    private final Map<BaseDimension, Integer> baseDimensions;

    private CompoundDimension(Map<BaseDimension, Integer> baseDimensions) {
        this.baseDimensions = Collections.unmodifiableMap(new EnumMap<>(baseDimensions));
    }

    static Dimension of(Map<BaseDimension, Integer> baseDimensions) {
        baseDimensions.entrySet().removeIf(e -> e.getValue() == 0);

        if (baseDimensions.isEmpty()) {
            return NONE;
        }

        if (baseDimensions.size() == 1) {
            Map.Entry<BaseDimension, Integer> entry = baseDimensions.entrySet().stream().findAny().get();
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return new CompoundDimension(baseDimensions);
    }

    static Dimension multiply(Map<BaseDimension, Integer> baseDimensions1,
                              Map<BaseDimension, Integer> baseDimensions2) {
        Map<BaseDimension, Integer> baseDimensions = new EnumMap<>(baseDimensions1);
        baseDimensions2.forEach(
                (dim_, n) -> baseDimensions.merge(dim_, n, Integer::sum)
        );
        return of(baseDimensions);
    }

    static Dimension inverse(Map<BaseDimension, Integer> baseDimensions) {
        Map<BaseDimension, Integer> baseDimensions_ = new EnumMap<>(BaseDimension.class);
        baseDimensions.forEach((dim, n) -> baseDimensions_.put(dim, -n));
        return of(baseDimensions_);
    }

    static Dimension divide(Map<BaseDimension, Integer> baseDimensions1,
                            Map<BaseDimension, Integer> baseDimensions2) {
        Map<BaseDimension, Integer> baseDimensions = new EnumMap<>(baseDimensions1);
        baseDimensions2.forEach(
                (dim_, n) -> baseDimensions.merge(dim_, -n,
                        (oldValue, newValue) -> oldValue - newValue)
        );
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
                throw new ArithmeticException();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dimension)) {
            return false;
        }
        Dimension that = (Dimension) o;
        return getBaseDimensions().equals(that.getBaseDimensions());
    }

    @Override
    public int hashCode() {
        return getBaseDimensions().hashCode();
    }
}
