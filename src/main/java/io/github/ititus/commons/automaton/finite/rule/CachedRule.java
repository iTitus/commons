package io.github.ititus.commons.automaton.finite.rule;

import java.util.Arrays;
import java.util.stream.IntStream;

abstract sealed class CachedRule implements Rule permits Not {

    protected int[] validCodepointCache;

    protected CachedRule() {
        this.validCodepointCache = null;
    }

    protected int[] populateCache() {
        if (validCodepointCache == null) {
            validCodepointCache = _validCodepoints().sorted().distinct().toArray();
        }

        return validCodepointCache;
    }

    @Override
    public IntStream validCodepoints() {
        return Arrays.stream(populateCache());
    }

    protected IntStream _validCodepoints() {
        return IntStream.rangeClosed(Rule.MIN_CODE_POINT, Rule.MAX_CODE_POINT).filter(this::accepts);
    }

    @Override
    public int validCodepointCount() {
        return populateCache().length;
    }
}
