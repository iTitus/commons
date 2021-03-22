package io.github.ititus.io;

import java.util.NoSuchElementException;

public enum HttpStatus {

    OK(200);

    private static final HttpStatus[] VALUES = values();

    private final int code;

    HttpStatus(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

    public boolean isOk() {
        return this == OK;
    }

    public static HttpStatus of(int code) {
        for (HttpStatus value : VALUES) {
            if (value.code == code) {
                return value;
            }
        }

        throw new NoSuchElementException("unknown http status code" + code);
    }
}
