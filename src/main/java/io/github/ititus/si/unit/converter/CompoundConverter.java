package io.github.ititus.si.unit.converter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        ArrayDeque<UnitConverter> queue = converters.stream()
                .map(UnitConverter::inverse)
                .collect(Collectors.toCollection(ArrayDeque::new));
        return new CompoundConverter(
                StreamSupport.stream(
                        Spliterators.spliterator(queue.descendingIterator(), queue.size(),
                                Spliterator.ORDERED | Spliterator.NONNULL | Spliterator.SIZED | Spliterator.SUBSIZED),
                        false)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public UnitConverter concat(UnitConverter converter) {
        return new CompoundConverter(Stream.concat(converters.stream(), Stream.of(converter)).collect(Collectors.toList()));
    }

    @Override
    public boolean isIdentity() {
        return false;
    }
}
