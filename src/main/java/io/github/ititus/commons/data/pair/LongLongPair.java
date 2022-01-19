package io.github.ititus.commons.data.pair;

import io.github.ititus.commons.data.pair.impl.LongLongPairImpl;

public interface LongLongPair extends Pair<Long, Long> {

    static LongLongPair of(long a, long b) {
        return new LongLongPairImpl(a, b);
    }

    long aLong();

    @Override
    default Long a() {
        return aLong();
    }

    long bLong();

    @Override
    default Long b() {
        return bLong();
    }

    @Override
    default String deepToString() {
        return toString();
    }
}
