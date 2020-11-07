package io.github.ititus.si.prefix;

public enum MetricPrefix implements Prefix {

    YOTTA("Y", 24),
    ZETTA("Z", 21),
    EXA("E", 18),
    PETA("P", 15),
    TERA("T", 12),
    GIGA("G", 9),
    MEGA("M", 6),
    KILO("k", 3),
    HECTO("h", 2),
    DEKA("da", 1),
    DECI("d", -1),
    CENTI("c", -2),
    MILLI("m", -3),
    MICRO("\u00b5", -6),
    NANO("n", -9),
    PICO("p", -12),
    FEMTO("f", -15),
    ATTO("a", -18),
    ZEPTO("z", -21),
    YOCTO("y", -24);

    private final String symbol;
    private final int exponent;

    MetricPrefix(String symbol, int exponent) {
        this.symbol = symbol;
        this.exponent = exponent;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int getBase() {
        return 10;
    }

    @Override
    public int getExponent() {
        return exponent;
    }
}
