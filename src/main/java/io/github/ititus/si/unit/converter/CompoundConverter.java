package io.github.ititus.si.unit.converter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompoundConverter implements UnitConverter {

    private final List<UnitConverter> converters;

    public static UnitConverter of(List<? extends UnitConverter> converters) {
        converters = converters.stream()
                .filter(c -> !c.isIdentity())
                .flatMap(c -> {
                    if (c instanceof CompoundConverter) {
                        return ((CompoundConverter) c).converters.stream();
                    }

                    return Stream.of(c);
                })
                .collect(Collectors.toList());

        if (converters.isEmpty()) {
            return IDENTITY;
        } else if (converters.size() == 1) {
            return converters.get(0);
        }

        converters = simplify(converters);

        return new CompoundConverter(converters);
    }

    private static List<? extends UnitConverter> simplify(List<? extends UnitConverter> converters) {
        // TODO: combine adjacent MultiplicationConverters
        return converters;
    }

    private CompoundConverter(Collection<? extends UnitConverter> converters) {
        this.converters = List.copyOf(converters);
    }

    @Override
    public double convert(double value) {
        for (UnitConverter converter : converters) {
            value = converter.convert(value);
        }
        return value;
    }

    @Override
    public UnitConverter inverse() {
        List<UnitConverter> list = converters.stream()
                .map(UnitConverter::inverse)
                .collect(Collectors.toList());
        Collections.reverse(list);
        return of(list);
    }

    @Override
    public UnitConverter concat(UnitConverter converter) {
        return of(Stream.concat(converters.stream(), Stream.of(converter)).collect(Collectors.toList()));
    }

    @Override
    public boolean isIdentity() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompoundConverter)) {
            return false;
        }
        CompoundConverter that = (CompoundConverter) o;
        return converters.equals(that.converters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(converters);
    }
}
