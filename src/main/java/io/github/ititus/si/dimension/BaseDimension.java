package io.github.ititus.si.dimension;

import io.github.ititus.data.Lazy;
import io.github.ititus.internal.Config;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public enum BaseDimension implements Dimension {

    LENGTH('L'),
    MASS('M'),
    TIME('T'),
    ELECTRIC_CURRENT('I'),
    TEMPERATURE(Config.FORCE_ASCII ? 'O' : '\u0398'),
    SUBSTANCE_AMOUNT('N'),
    LUMINOUS_INTENSITY('J');

    private final char symbol;
    private final Lazy<Map<BaseDimension, Integer>> baseDimensions;

    BaseDimension(char symbol) {
        this.symbol = symbol;
        this.baseDimensions = Lazy.of(() -> Collections.unmodifiableMap(new EnumMap<>(Map.of(this, 1))));
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String getString() {
        return "[" + getSymbol() + "]";
    }

    @Override
    public Map<BaseDimension, Integer> getBaseDimensions() {
        return baseDimensions.get();
    }
}
