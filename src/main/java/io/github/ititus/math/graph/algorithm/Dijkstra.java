package io.github.ititus.math.graph.algorithm;

import io.github.ititus.math.graph.Edge;
import io.github.ititus.math.graph.Graph;
import io.github.ititus.math.graph.Vertex;
import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    private final Comparator<Vertex<T>> distanceComparator;

    public Dijkstra(Graph<T> graph, Vertex<T> start) {
        this.graph = graph;
        this.start = start;
        this.r = new Result();
        this.distanceComparator = (v1, v2) -> {
            Optional<BigRational> r1 = r.getDist(v1);
            Optional<BigRational> r2 = r.getDist(v2);

            if (r1.isPresent()) {
                return r2.map(r -> r1.get().compareTo(r)).orElse(-1);
            } else if (r2.isPresent()) {
                return 1;
            }

            return 0;
        };
    }

    public Result findShortestPaths() {
        if (!r.done) {
            Set<Vertex<T>> unvisited = new HashSet<>(graph.getVertices());
            r.setDist(start, BigRationalConstants.ZERO);

            while (!unvisited.isEmpty()) {
                Vertex<T> u = Collections.min(unvisited, distanceComparator);
                unvisited.remove(u);

                Optional<BigRational> distOpt = r.getDist(u);
                if (distOpt.isEmpty()) {
                    // throw new RuntimeException("unreachable vertex " + u);
                    continue;
                }

                BigRational dist = distOpt.get();

                for (Edge<T> e : graph.getAdjacentEdges(u)) {
                    Vertex<T> v = e.getStart().equals(u) ? e.getEnd() : e.getStart();
                    if (!unvisited.contains(v)) {
                        continue;
                    }

                    BigRational newDist = dist.add(e.getWeight());
                    Optional<BigRational> oldDist = r.getDist(v);
                    if (oldDist.isEmpty() || newDist.compareTo(oldDist.get()) < 0) {
                        r.setDist(v, newDist);
                        r.setPrev(v, u);
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

    private String resultToString(Result r) {
        List<Vertex<T>> vertices = graph.getVertices().stream()
                .filter(Predicate.not(start::equals))
                .toList();
        StringBuilder b = new StringBuilder();
        for (Vertex<T> v : vertices) {
            b.append(v.get()).append('=');

            Optional<BigRational> dist = r.getDist(v);
            if (dist.isPresent()) {
                b.append(dist.get()).append(", ").append(r.getPrev(v));
            } else {
                b.append("∞");
            }

            b.append(" | ");
        }

        b.setLength(b.length() - 3);
        return b.toString();
    }

    public class Result {

        private final Map<T, BigRational> dist;
        private final Map<T, Vertex<T>> prev;
        private boolean done;

        private Result() {
            this.dist = new HashMap<>();
            this.prev = new HashMap<>();
            this.done = false;
        }

        private void setDist(Vertex<T> v, BigRational r) {
            dist.put(v.get(), r);
        }

        private void setPrev(Vertex<T> v, Vertex<T> w) {
            prev.put(v.get(), w);
        }

        private Optional<BigRational> getDist(Vertex<T> v) {
            return Optional.ofNullable(dist.get(v.get()));
        }

        private Optional<Vertex<T>> getPrev(Vertex<T> v) {
            return Optional.ofNullable(prev.get(v.get()));
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
            return getShortestPathEdges(end).stream()
                    .map(Edge::getWeight)
                    .reduce(BigRationalConstants.ZERO, BigRational::add);
        }
    }
}
