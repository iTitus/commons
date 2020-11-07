package io.github.ititus.unit;

public interface Quantity<Q extends QuantityType<Q>> {

    double getValue();

    Unit<Q> getUnit();

    Quantity<Q> convertTo(Unit<Q> unit);

    @SuppressWarnings("unchecked")
    default <T extends QuantityType<T>> Quantity<T> as(QuantityType<T> type) throws ClassCastException {
        if (!getUnit().getDimension().isCommensurableWith(type.getDimension())) {
            throw new ClassCastException();
        }

        return (Quantity<T>) this;
    }

    Quantity<Q> add(Quantity<Q> qty);

    Quantity<Q> negate();

    Quantity<Q> subtract(Quantity<Q> qty);

    Quantity<Q> multiply(double d);

    Quantity<?> multiply(Quantity<?> qty);

    Quantity<?> inverse();

    Quantity<Q> divide(double d);

    Quantity<?> divide(Quantity<?> qty);

    Quantity<?> pow(int n);

    Quantity<?> root(int n);

}
