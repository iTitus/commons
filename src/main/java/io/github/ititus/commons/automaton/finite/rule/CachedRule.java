package io.github.ititus.commons.automaton.finite.rule;

import io.github.ititus.commons.data.ArrayUtil;
import io.github.ititus.commons.data.StreamUtil;

import java.util.stream.IntStream;

abstract sealed class CachedRule implements Rule permits Not {

    protected char[] validCharCache;

    protected CachedRule() {
        this.validCharCache = null;
    }

    protected char[] populateCache() {
        if (validCharCache == null) {
            validCharCache = StreamUtil.toCharArray(_validCodepoints().sorted().distinct());
        }

        return validCharCache;
    }

    @Override
    public IntStream validChars() {
        return ArrayUtil.stream(populateCache());
    }

    protected IntStream _validCodepoints() {
        return IntStream.rangeClosed(Rule.MIN_VALUE, Rule.MAX_VALUE)
                .filter(c -> accepts((char) c));
    }

    @Override
    public int validCharCount() {
        return populateCache().length;
    }
}
