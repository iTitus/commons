package io.github.ititus.parser;

import java.util.Objects;

public final class ParserResult<IN, OUT> {

    private final IN remaining;
    private final OUT result;

    public ParserResult(IN remaining, OUT result) {
        this.remaining = remaining;
        this.result = result;
    }

    public IN getRemaining() {
        return remaining;
    }

    public OUT getResult() {
        return result;
    }

    public static <IN, OUT> ParserResult<IN, OUT> of(IN remaining, OUT result) {
        return new ParserResult<>(remaining, result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParserResult)) {
            return false;
        }
        ParserResult<?, ?> that = (ParserResult<?, ?>) o;
        return remaining.equals(that.remaining) && result.equals(that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remaining, result);
    }

    @Override
    public String toString() {
        return "ParserResult{remaining=" + remaining + ", result=" + result + "}";
    }
}
