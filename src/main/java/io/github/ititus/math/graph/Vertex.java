package io.github.ititus.math.graph;

import java.util.Objects;
import java.util.UUID;

public final class Vertex<T> {

    private final Graph<T> graph;
    private final UUID uuid;
    private final T t;

    Vertex(Graph<T> graph, UUID uuid, T t) {
        this.graph = Objects.requireNonNull(graph);
        this.uuid = Objects.requireNonNull(uuid);
        this.t = Objects.requireNonNull(t);
    }

    public Graph<T> getGraph() {
        return graph;
    }

    public UUID getUuid() {
        return uuid;
    }

    public T get() {
        return t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vertex)) {
            return false;
        }
        Vertex<?> v = (Vertex<?>) o;
        return graph == v.graph && uuid.equals(v.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return t.toString();
    }
}
