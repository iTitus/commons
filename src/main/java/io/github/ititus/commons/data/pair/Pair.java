package io.github.ititus.commons.data.pair;

import io.github.ititus.commons.data.DeepToString;

public sealed interface Pair<A, B> extends DeepToString permits IntIntPair, IntObjPair, LongLongPair, ObjObjPair {

    static <A, B> Pair<A, B> of(A a, B b) {
        return ObjObjPair.of(a, b);
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
