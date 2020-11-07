package io.github.ititus.si.prefix;

public enum BinaryPrefix implements Prefix {

    KIBI("Ki", 1),
    MEBI("Mi", 2),
    GIBI("Gi", 3),
    TEBI("Ti", 4),
    PEBI("Pi", 5),
    EXBI("Ei", 6),
    ZEBI("Zi", 7),
    YOBI("Yi", 8);

    private final String symbol;
    private final int exponent;

    BinaryPrefix(String symbol, int exponent) {
        this.symbol = symbol;
        this.exponent = exponent;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int getBase() {
        return 1024;
    }

    @Override
    public int getExponent() {
        return exponent;
    }
}
