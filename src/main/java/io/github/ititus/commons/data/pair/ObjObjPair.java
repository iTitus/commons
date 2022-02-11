package io.github.ititus.commons.data.pair;

public sealed interface ObjObjPair<A, B> extends Pair<A, B> permits ObjObjPairImpl {

    static <A, B> ObjObjPair<A, B> of(A a, B b) {
        return new ObjObjPairImpl<>(a, b);
    }
}
