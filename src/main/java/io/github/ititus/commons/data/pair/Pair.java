package io.github.ititus.commons.data.pair;

import io.github.ititus.commons.data.DeepToString;
import io.github.ititus.commons.data.pair.impl.PairImpl;

public interface Pair<A, B> extends DeepToString {

    static <A, B> Pair<A, B> of(A a, B b) {
        return new PairImpl<>(a, b);
    }

    static <B> IntObjPair<B> of(int a, B b) {
        return IntObjPair.of(a, b);
    }

    static IntIntPair of(int a, int b) {
        return IntIntPair.of(a, b);
    }

    static LongLongPair of(long a, long b) {
        return LongLongPair.of(a, b);
    }

    A a();

    B b();

}
