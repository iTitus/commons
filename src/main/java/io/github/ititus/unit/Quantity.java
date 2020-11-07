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

    @SuppressWarnings("unchecked")
    default <T extends QuantityType<T>> Quantity<T> as(Unit<T> unit) throws ClassCastException {
        if (!getUnit().getDimension().isCommensurableWith(unit.getDimension())) {
            throw new ClassCastException();
        }

        return ((Quantity<T>) this).convertTo(unit);
    }

    default Quantity<Q> add(Quantity<Q> qty) {
        return getUnit().get(getValue() + qty.convertTo(getUnit()).getValue());
    }

    default Quantity<Q> negate() {
        return getUnit().get(-getValue());
    }

    default Quantity<Q> subtract(Quantity<Q> qty) {
        return add(qty.negate());
    }

    default Quantity<Q> multiply(double d) {
        return getUnit().get(getValue() * d);
    }

    Quantity<?> multiply(Quantity<?> qty);

    Quantity<?> inverse();

    default Quantity<Q> divide(double d) {
        return getUnit().get(getValue() / d);
    }

    default Quantity<?> divide(Quantity<?> qty) {
        return multiply(qty.inverse());
    }

    Quantity<?> pow(int n);

    Quantity<?> root(int n);

}
