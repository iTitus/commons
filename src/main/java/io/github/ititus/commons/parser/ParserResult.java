package io.github.ititus.commons.parser;

import java.util.Objects;
import java.util.function.Function;

public record ParserResult<I, R>(Input<I> remaining, R result) {

    public ParserResult {
        Objects.requireNonNull(remaining);
        Objects.requireNonNull(result);
    }

    public static <I, R> ParserResult<I, R> of(Input<I> remaining, R result) {
        return new ParserResult<>(remaining, result);
    }

    public <S> ParserResult<I, S> map(Function<? super R, ? extends S> f) {
        return of(remaining, f.apply(result));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof ParserResult)) {
            return false;
        }

        ParserResult<?, ?> that = (ParserResult<?, ?>) o;
        return remaining.equals(that.remaining) && result.equals(that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remaining, result);
    }
}
