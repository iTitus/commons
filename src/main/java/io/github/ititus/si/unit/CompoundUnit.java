package io.github.ititus.si.unit;

import io.github.ititus.si.dimension.Dimension;
import io.github.ititus.si.prefix.Prefix;
import io.github.ititus.si.quantity.type.QuantityType;
import io.github.ititus.si.quantity.value.QuantityValue;
import io.github.ititus.si.unit.converter.UnitConverter;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static io.github.ititus.si.quantity.type.Unknown.UNKNOWN;
import static io.github.ititus.si.unit.Units.ONE;

final class CompoundUnit<Q extends QuantityType<Q>> extends AbstractUnit<Q> {

    private final Map<Unit<?>, Integer> units;

    private CompoundUnit(Q type, Dimension dimension, Map<Unit<?>, Integer> units) {
        super(type, dimension);
        this.units = units;
    }

    private static Unit<?> of(Dimension dimension, Map<Unit<?>, Integer> units) {
        units = new LinkedHashMap<>(units);

        units.entrySet().removeIf(e -> e.getValue() == 0);
        units.entrySet().removeIf(e -> e.getKey() instanceof BaseUnit && e.getKey().getDimension().equals(Dimension.NONE));

        if (units.isEmpty()) {
            return ONE;
        }

        if (units.size() == 1) {
            Map.Entry<Unit<?>, Integer> entry = units.entrySet().stream().findAny().get();
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return new CompoundUnit<>(UNKNOWN, dimension, Collections.unmodifiableMap(units));
    }

    static Unit<?> ofProduct(Unit<?> u1, Unit<?> u2) {
        Map<Unit<?>, Integer> units;
        if (u1 instanceof CompoundUnit) {
            units = new LinkedHashMap<>(((CompoundUnit<?>) u1).units);
        } else {
            units = new LinkedHashMap<>(Map.of(u1, 1));
        }

        if (u2 instanceof CompoundUnit) {
            ((CompoundUnit<?>) u2).units.forEach((u, n) -> units.merge(u, n, Integer::sum));
        } else {
            units.merge(u2, 1, Integer::sum);
        }

        return of(u1.getDimension().multiply(u2.getDimension()), units);
    }

    static Unit<?> inverse(Unit<?> u) {
        Map<Unit<?>, Integer> units = new LinkedHashMap<>();
        if (u instanceof CompoundUnit) {
            ((CompoundUnit<?>) u).units.forEach((u_, n) -> units.put(u_, -n));
        } else {
            units.put(u, -1);
        }

        return of(u.getDimension().inverse(), units);
    }

    static Unit<?> ofPow(Unit<?> u, int power) {
        Map<Unit<?>, Integer> units = new LinkedHashMap<>();
        if (u instanceof CompoundUnit) {
            ((CompoundUnit<?>) u).units.forEach((u_, n) -> units.put(u_, n * power));
        } else {
            units.put(u, power);
        }

        return of(u.getDimension().pow(power), units);
    }

    static Unit<?> ofRoot(Unit<?> u, int root) {
        Map<Unit<?>, Integer> units = new LinkedHashMap<>();
        if (u instanceof CompoundUnit) {
            ((CompoundUnit<?>) u).units.forEach((u_, n) -> {
                if (n % root != 0) {
                    throw new ArithmeticException();
                }
                units.put(u_, n / root);
            });
        } else {
            throw new ArithmeticException();
        }

        return of(u.getDimension().root(root), units);
    }

    @Override
    public String getSymbol() {
        StringBuilder b = new StringBuilder();

        units.forEach((u, n) -> {
            b.append(u.getSymbol());

            if (n != 1) {
                b.append('^').append(n);
            }
        });

        return b.toString();
    }

    @Override
    public <T extends QuantityType<T>> UnitConverter getConverterTo(Unit<T> unit) {
        if (!isCommensurableWith(unit.getType())) {
            throw new ClassCastException();
        } else if (equals(unit)) {
            return UnitConverter.IDENTITY;
        }

        Unit<Q> stdUnit = getType().getStandardUnit();

        UnitConverter toStd = units.entrySet().stream()
                .flatMap(e -> {
                    int power = e.getValue();
                    Unit<?> u = e.getKey();
                    Unit<?> stdU = u.getType().getStandardUnit();

                    UnitConverter converter = u.getConverterTo(stdU);
                    UnitConverter finalHack;

                    if (power == 0) {
                        return Stream.empty();
                    } else if (power < 0) {
                        power = -power;
                        finalHack = converter.inverse();
                    } else {
                        finalHack = converter;
                    }

                    return IntStream.range(0, power).mapToObj(i -> finalHack);
                })
                .reduce(UnitConverter.IDENTITY, UnitConverter::concat);

        return toStd.concat(unit.getConverterTo(stdUnit).inverse());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends QuantityType<T>> Unit<T> as(T type) {
        if (!isCommensurableWith(type)) {
            throw new ClassCastException();
        } else if (getType().equals(type)) {
            return (Unit<T>) this;
        }

        return new CompoundUnit<>(type, getDimension(), units);
    }

    @Override
    public Unit<Q> multiply(QuantityValue v) {
        return ConvertedUnit.of(this, UnitConverter.factor(v));
    }

    @Override
    public Unit<?> multiply(Unit<?> unit) {
        return ofProduct(this, unit);
    }

    @Override
    public Unit<?> inverse() {
        return inverse(this);
    }

    @Override
    public Unit<?> pow(int n) {
        return ofPow(this, n);
    }

    @Override
    public Unit<?> root(int n) {
        return ofRoot(this, n);
    }

    @Override
    public Unit<Q> alternate(String symbol) {
        return new AlternateUnit<>(this, symbol);
    }

    @Override
    public Unit<Q> prefix(Prefix prefix) {
        return new PrefixUnit<>(this, prefix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompoundUnit)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        CompoundUnit<?> that = (CompoundUnit<?>) o;
        return units.equals(that.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), units);
    }
}
