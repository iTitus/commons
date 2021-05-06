package io.github.ititus.data;

import java.util.Enumeration;
import java.util.Iterator;

public final class ImmutableIterator<T> implements Iterator<T>, Enumeration<T> {

    private final Iterator<? extends T> it;

    public ImmutableIterator(Iterator<? extends T> it) {
        this.it = it;
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public T next() {
        return it.next();
    }

    @Override
    public boolean hasMoreElements() {
        return hasNext();
    }

    @Override
    public T nextElement() {
        return next();
    }

    @Override
    public Iterator<T> asIterator() {
        return this;
    }
}
