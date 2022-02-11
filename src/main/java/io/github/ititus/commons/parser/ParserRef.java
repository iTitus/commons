package io.github.ititus.commons.parser;

import io.github.ititus.commons.data.either.Either;

public interface ParserRef<I, R> extends Parser<I, R> {

    void set(Parser<I, ? extends R> p);

    Parser<I, R> get();

    @Override
    default Either<ParserError, ParserResult<I, R>> parse(Input<I> in) {
        Parser<I, R> p = get();
        if (p == null) {
            throw new IllegalStateException();
        }

        return p.parse(in);
    }
}
