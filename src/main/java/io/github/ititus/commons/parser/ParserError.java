package io.github.ititus.commons.parser;

public class ParserError extends RuntimeException {

    public ParserError() {
        super();
    }

    public ParserError(String message) {
        super(message);
    }

    public ParserError(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserError(Throwable cause) {
        super(cause);
    }

    protected ParserError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
