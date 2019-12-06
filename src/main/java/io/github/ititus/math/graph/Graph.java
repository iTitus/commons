package io.github.ititus.math.graph;

import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Undirected Graph
 *
 * @param <T> content type
 */
public class Graph<T> {

    private final Set<Vertex<T>> vertices;
    private final Set<Edge<T>> edges;

    public Graph() {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
    }

    public Vertex<T> addVertex(T t) {
        if (getVertex(Objects.requireNonNull(t)).isPresent()) {
            throw new IllegalArgumentException("vertex already exists");
        }

        UUID uuid;
        do {
            uuid = UUID.randomUUID();
        } while (getVertexById(uuid).isPresent());

        Vertex<T> v = new Vertex<>(this, uuid, t);
        vertices.add(v);
        return v;
    }

    public Edge<T> addEdge(Vertex<T> start, Vertex<T> end) {
        return addEdge(start, end, BigRationalConstants.ONE);
    }

    public Edge<T> addEdge(Vertex<T> start, Vertex<T> end, BigRational weight) {
        if (Objects.requireNonNull(start).equals(Objects.requireNonNull(end))) {
            throw new IllegalArgumentException("start=end");
        } else if (getEdgeByVertices(start, end).isPresent()) {
            throw new IllegalArgumentException("edge already exists");
        } else if (!Objects.requireNonNull(weight).isPositive()) {
            throw new IllegalArgumentException("weight");
        }

        UUID uuid;
        do {
            uuid = UUID.randomUUID();
        } while (getEdgeById(uuid).isPresent());

        Edge<T> e = new Edge<>(this, uuid, start, end, weight);
        edges.add(e);
        return e;
    }

    public Set<Vertex<T>> getVertices() {
        return vertices;
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }

    public Optional<Vertex<T>> getVertex(T t) {
        Objects.requireNonNull(t);
        return vertices.stream()
                .filter(v ->
                        v.get().equals(t)
                )
                .findAny();
    }

    public Optional<Vertex<T>> getVertexById(UUID uuid) {
        Objects.requireNonNull(uuid);
        return vertices.stream()
                .filter(v ->
                        v.getUuid().equals(uuid)
                )
                .findAny();
    }

    public Optional<Edge<T>> getEdge(T start, T end) {
        return getEdgeByVertices(getVertex(start).orElseThrow(), getVertex(end).orElseThrow());
    }

    public Optional<Edge<T>> getEdgeByVertices(Vertex<T> start, Vertex<T> end) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        return edges.stream()
                .filter(e ->
                        (e.getStart().equals(start) && e.getEnd().equals(end))
                                || (e.getStart().equals(end) && e.getEnd().equals(start))
                )
                .findAny();
    }

    public Optional<Edge<T>> getEdgeById(UUID uuid) {
        Objects.requireNonNull(uuid);
        return edges.stream()
                .filter(e ->
                        e.getUuid().equals(uuid)
                )
                .findAny();
    }

    public Set<Vertex<T>> getNeighborVertices(Vertex<T> v) {
        Objects.requireNonNull(v);
        return vertices.stream()
                .filter(w ->
                        getEdgeByVertices(v, w).isPresent()
                )
                .collect(Collectors.toSet());
    }

    public Set<Edge<T>> getAdjacentEdges(Vertex<T> v) {
        Objects.requireNonNull(v);
        return edges.stream()
                .filter(e ->
                        e.getStart().equals(v) || e.getEnd().equals(v)
                )
                .collect(Collectors.toSet());
    }

    public Set<Edge<T>> getOutgoingEdges(Vertex<T> v) {
        Objects.requireNonNull(v);
        return edges.stream()
                .filter(e ->
                        e.getStart().equals(v)
                )
                .collect(Collectors.toSet());
    }

    public Set<Edge<T>> getIncomingEdges(Vertex<T> v) {
        Objects.requireNonNull(v);
        return edges.stream()
                .filter(e ->
                        e.getEnd().equals(v)
                )
                .collect(Collectors.toSet());
    }
}
