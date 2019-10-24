package io.github.ititus.math.graph.algorithm;

import io.github.ititus.math.graph.Edge;
import io.github.ititus.math.graph.Graph;
import io.github.ititus.math.graph.Vertex;
import io.github.ititus.math.number.BigRational;
import io.github.ititus.math.number.BigRationalConstants;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Dijkstra<T> {

    private final Graph<T> graph;
    private final Vertex<T> start;

    public Dijkstra(Graph<T> graph, Vertex<T> start) {
        this.graph = graph;
        this.start = start;
    }

    public Result findShortestPaths() {
        Result r = new Result();

        Set<UUID> unvisited = graph.getVertices().stream().map(Vertex::getUuid).collect(Collectors.toSet());
        r.setDist(start, BigRationalConstants.ZERO);

        while (!unvisited.isEmpty()) {
            Vertex<T> v = findMinDist(unvisited, r);

            Optional<BigRational> distVOpt = r.getDist(v);
            if (distVOpt.isEmpty()) {
                throw new RuntimeException("unreachable vertex " + v);
            }
            BigRational distV = distVOpt.get();

            Set<Edge<T>> adjEdges = graph.getAdjacentEdges(v);
            for (Edge<T> e : adjEdges) {
                Vertex<T> w = e.getStart().equals(v) ? e.getEnd() : e.getStart();

                BigRational distance = distV.add(e.getWeight());
                Optional<BigRational> distW = r.getDist(w);
                if (distW.isEmpty() || distance.compareTo(distW.get()) < 0) {
                    r.setDist(w, distance);
                    r.setPrev(w, v);
                }
            }

            System.out.printf("visiting=%s | %s%n", v, resultToString(r));
        }

        return r;
    }

    private Vertex<T> findMinDist(Set<UUID> unvisited, Result res) {
        UUID min = Collections.min(unvisited, (u1, u2) -> {
            Optional<BigRational> r1 = res.getDist(u1);
            Optional<BigRational> r2 = res.getDist(u2);

            if (r1.isPresent()) {
                return r2.map(r -> r1.get().compareTo(r)).orElse(-1);
            } else if (r2.isPresent()) {
                return 1;
            }
            return 0;
        });

        unvisited.remove(min);

        //noinspection OptionalGetWithoutIsPresent
        return graph.getVertex(min).get();
    }

    @SuppressWarnings("unchecked")
    private String resultToString(Result r) {
        List<Vertex<T>> vertices = graph.getVertices().stream().filter(Predicate.not(start::equals)).sorted((v1, v2) -> {
            if (v1.get() instanceof Comparable<?>) {
                return ((Comparable<T>) v1.get()).compareTo(v2.get());
            }
            return v1.getUuid().compareTo(v2.getUuid());
        }).collect(Collectors.toList());

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

        private final Map<UUID, BigRational> dist;
        private final Map<UUID, UUID> prev;

        private Result() {
            this.dist = new HashMap<>();
            this.prev = new HashMap<>();
        }

        private void setDist(Vertex<T> v, BigRational r) {
            dist.put(v.getUuid(), r);
        }

        private void setPrev(Vertex<T> v, Vertex<T> w) {
            prev.put(v.getUuid(), w.getUuid());
        }

        private Optional<BigRational> getDist(Vertex<T> v) {
            return getDist(v.getUuid());
        }

        private Optional<BigRational> getDist(UUID uuid) {
            return Optional.ofNullable(dist.get(uuid));
        }

        private Vertex<T> getPrev(Vertex<T> v) {
            //noinspection OptionalGetWithoutIsPresent
            return graph.getVertex(prev.get(v.getUuid())).get();
        }

        public List<Vertex<T>> getShortestPathVertices(Vertex<T> end) {
            List<Vertex<T>> list = new ArrayList<>();
            list.add(end);

            Vertex<T> v = end;
            while (!v.equals(start)) {
                v = getPrev(v);
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
                w = getPrev(v);
                //noinspection OptionalGetWithoutIsPresent
                list.add(graph.getEdge(v, w).get());

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
