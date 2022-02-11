package io.github.ititus.commons.data.pair;

public sealed interface IntIntPair extends Pair<Integer, Integer> permits IntIntPairImpl {

    static IntIntPair of(int a, int b) {
        return new IntIntPairImpl(a, b);
    }

    int aInt();

    @Override
    default Integer a() {
        return aInt();
    }

    int bInt();

    @Override
    default Integer b() {
        return bInt();
    }

    @Override
    default String deepToString() {
        return toString();
    }
}
