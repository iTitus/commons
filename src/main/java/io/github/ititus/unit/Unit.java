package io.github.ititus.unit;

public interface Unit<Q extends QuantityType<Q>> {

    Dimension getDimension();

    UnitConverter getConverterTo(Unit<Q> unit);

    Quantity<Q> get(double value);

    @SuppressWarnings("unchecked")
    default <T extends QuantityType<T>> Unit<T> as(QuantityType<T> type) throws ClassCastException {
        if (!getDimension().isCommensurableWith(type.getDimension())) {
            throw new ClassCastException();
        }

        return (Unit<T>) this;
    }

    Unit<Q> multiply(double d);

    Unit<?> multiply(Unit<?> unit);

    Unit<?> inverse();

    Unit<Q> divide(double d);

    Unit<?> divide(Unit<?> unit);

    Unit<?> pow(int n);

    Unit<?> root(int n);

}
