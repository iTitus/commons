package io.github.ititus.commons.data.pair;

public sealed interface LongLongPair extends Pair<Long, Long> permits LongLongPairImpl {

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
