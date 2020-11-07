package io.github.ititus.unit;

public class Dimensionless extends AbstractQuantityType<Dimensionless> {

    public static final Dimensionless DIMENSIONLESS = new Dimensionless();

    private Dimensionless() {
        super(Dimension.NONE);
    }
}
