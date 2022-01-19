package io.github.ititus.commons.si;

public class NotCommensurableException extends RuntimeException {

    public NotCommensurableException() {
        super();
    }

    public NotCommensurableException(String message) {
        super(message);
    }

    public NotCommensurableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCommensurableException(Throwable cause) {
        super(cause);
    }

    protected NotCommensurableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
