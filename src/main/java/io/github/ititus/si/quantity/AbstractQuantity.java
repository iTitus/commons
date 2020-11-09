package io.github.ititus.si.quantity;

import io.github.ititus.si.quantity.type.QuantityType;

public abstract class AbstractQuantity<Q extends QuantityType<Q>> implements Quantity<Q> {

    @Override
    public String toString() {
        return "Quantity{" +
                "value=" + getValue() +
                ", unit=" + getUnit() +
                '}';
    }
}
