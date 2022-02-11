package io.github.ititus.commons.data.pair;

public sealed interface IntObjPair<B> extends Pair<Integer, B> permits IntObjPairImpl {

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
