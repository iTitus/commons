package io.github.ititus.commons.assertions;

import io.github.ititus.commons.data.either.Either;
import org.assertj.core.api.AbstractAssert;

public class EitherAssert<L, R> extends AbstractAssert<EitherAssert<L, R>, Either<L, R>> {

    public EitherAssert(Either<L, R> actual) {
        super(actual, EitherAssert.class);
    }
}
