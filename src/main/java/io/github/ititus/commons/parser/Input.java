package io.github.ititus.commons.parser;

import java.util.List;

public interface Input<T> {

    static Input<Character> of(CharSequence s) {
        return new CharSequenceInput(s);
    }

    boolean isEmpty();

    int pos();

    default T get() {
        return get(1).get(0);
    }

    List<T> get(int n);

    default Input<T> next() {
        return next(1);
    }

    Input<T> next(int n);

}
