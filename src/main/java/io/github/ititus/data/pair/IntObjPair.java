package io.github.ititus.data.pair;

import io.github.ititus.data.pair.impl.IntObjPairImpl;

public interface IntObjPair<B> extends Pair<Integer, B> {

    static <B> IntObjPair<B> of(int a, B b) {
        return new IntObjPairImpl<>(a, b);
    }

    int aInt();

    @Override
    default Integer a() {
        return aInt();
    }

    @Override
    default String deepToString() {
        return toString();
    }
}
