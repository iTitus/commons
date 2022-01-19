package io.github.ititus.commons.si.dimension;

import io.github.ititus.commons.data.Lazy;
import io.github.ititus.commons.internal.Config;

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
        this.baseDimensions = Lazy.of(() -> Map.of(this, 1));
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
