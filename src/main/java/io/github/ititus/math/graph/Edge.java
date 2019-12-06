package io.github.ititus.math.graph;

import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;

import java.util.Objects;
import java.util.UUID;

/**
 * Undirected Edge
 *
 * @param <T> content type
 */
public final class Edge<T> {

    private final Graph<T> graph;
    private final UUID uuid;
    private final Vertex<T> start, end;
    private final BigRational weight;

    Edge(Graph<T> graph, UUID uuid, Vertex<T> start, Vertex<T> end, BigRational weight) {
        if (Objects.requireNonNull(start).equals(Objects.requireNonNull(end))) {
            throw new IllegalArgumentException("start=end");
        } else if (Objects.requireNonNull(weight).compareTo(BigRationalConstants.ONE) < 0) {
            throw new IllegalArgumentException("weight");
        }

        this.graph = Objects.requireNonNull(graph);
        this.uuid = Objects.requireNonNull(uuid);
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Graph<T> getGraph() {
        return graph;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Vertex<T> getStart() {
        return start;
    }

    public Vertex<T> getEnd() {
        return end;
    }

    public BigRational getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge<?> e = (Edge<?>) o;
        return graph == e.graph && uuid.equals(e.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return start + " -[" + weight + "]- " + end;
    }
}
