package io.github.ititus.math.graph.algorithm;

import io.github.ititus.data.pair.Pair;
import io.github.ititus.math.graph.Edge;
import io.github.ititus.math.graph.Graph;
import io.github.ititus.math.graph.Vertex;
import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Undirected Dijkstra algorithm
 *
 * @param <T> content type
 */
public class Dijkstra<T> {

    private static final boolean PRINT_DEBUG_INFO = false;

    private final Graph<T> graph;
    private final Vertex<T> start;
    private final Result r;

    public Dijkstra(Graph<T> graph, Vertex<T> start) {
        if (graph.getVertices().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.graph = graph;
        this.start = start;
        this.r = new Result();
    }

    public Result findShortestPaths() {
        if (!r.done) {
            PriorityQueue<Pair<BigRational, Vertex<T>>> q = new PriorityQueue<>(Comparator.comparing(Pair::a));
            r.setDist(start, BigRationalConstants.ZERO);
            q.add(Pair.of(BigRationalConstants.ZERO, start));

            while (!q.isEmpty()) {
                Pair<BigRational, Vertex<T>> pair = q.remove();
                BigRational dist = pair.a();
                Vertex<T> u = pair.b();
                if (!dist.equals(r.getDist(u).orElseThrow())) {
                    continue;
                }

                for (Edge<T> e : graph.getAdjacentEdges(u)) {
                    Vertex<T> v = e.getEnd();
                    BigRational newDist = dist.add(e.getWeight());
                    Optional<BigRational> oldDist = r.getDist(v);
                    if (oldDist.isEmpty() || newDist.compareTo(oldDist.get()) < 0) {
                        r.setDist(v, newDist);
                        r.setPrev(v, u);

                        q.add(Pair.of(newDist, v));
                    }
                }

                if (PRINT_DEBUG_INFO) {
                    System.out.printf("visiting=%s | %s%n", u, resultToString(r));
                }
            }

            r.done = true;
        }

        return r;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private String resultToString(Result r) {
        Stream<Vertex<T>> stream = graph.getVertices().stream();
        if (graph.getVertices().iterator().next().get() instanceof Comparable) {
            stream = stream.sorted(Comparator.comparing(v -> (Comparable) v.get()));
        }
        List<Vertex<T>> vertices = stream.toList();
        StringBuilder b = new StringBuilder();
        for (Vertex<T> v : vertices) {
            b.append(v.get()).append('=');

            Optional<BigRational> dist = r.getDist(v);
            if (dist.isPresent()) {
                b.append(dist.get()).append(", ").append(r.getPrev(v));
            } else {
                b.append('\u221e');
            }

            b.append(" | ");
        }

        b.setLength(b.length() - 3);
        return b.toString();
    }

    public class Result {

        private final Map<Vertex<T>, BigRational> dist;
        private final Map<Vertex<T>, Vertex<T>> prev;
        private boolean done;

        private Result() {
            this.dist = new HashMap<>();
            this.prev = new HashMap<>();
            this.done = false;
        }

        private void setDist(Vertex<T> v, BigRational r) {
            dist.put(v, r);
        }

        private void setPrev(Vertex<T> v, Vertex<T> w) {
            prev.put(v, w);
        }

        private Optional<BigRational> getDist(Vertex<T> v) {
            return Optional.ofNullable(dist.get(v));
        }

        private Optional<Vertex<T>> getPrev(Vertex<T> v) {
            return Optional.ofNullable(prev.get(v));
        }

        public List<Vertex<T>> getShortestPathVertices(Vertex<T> end) {
            List<Vertex<T>> list = new ArrayList<>();
            list.add(end);

            Vertex<T> v = end;
            while (!v.equals(start)) {
                v = getPrev(v).orElseThrow();
                list.add(v);
            }

            Collections.reverse(list);
            return list;
        }

        public List<T> getShortestPathObjects(Vertex<T> end) {
            return getShortestPathVertices(end).stream()
                    .map(Vertex::get)
                    .collect(Collectors.toList());
        }

        public List<Edge<T>> getShortestPathEdges(Vertex<T> end) {
            List<Edge<T>> list = new ArrayList<>();

            Vertex<T> v = end, w;
            while (!v.equals(start)) {
                w = getPrev(v).orElseThrow();
                list.add(graph.getEdgeByVertices(v, w).orElseThrow());

                v = w;
            }

            Collections.reverse(list);
            return list;
        }

        public BigRational getShortestPathLength(Vertex<T> end) {
            return r.getDist(end).orElseThrow();
        }
    }
}
