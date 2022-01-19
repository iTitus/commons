package io.github.ititus.commons.precondition;

public final class PreconditionViolatedException extends RuntimeException {

    PreconditionViolatedException() {
        super();
    }

    PreconditionViolatedException(String message) {
        super(message);
    }

    PreconditionViolatedException(String message, Throwable cause) {
        super(message, cause);
    }

    PreconditionViolatedException(Throwable cause) {
        super(cause);
    }
}
