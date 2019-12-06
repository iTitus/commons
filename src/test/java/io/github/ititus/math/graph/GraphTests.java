package io.github.ititus.math.graph;

import io.github.ititus.math.graph.algorithm.Dijkstra;
import io.github.ititus.math.number.BigRational;

public class GraphTests {

    public static void main(String[] args) {
        Graph<String> g = new Graph<>();

        Vertex<String> a = g.addVertex("A");
        Vertex<String> b = g.addVertex("B");
        Vertex<String> c = g.addVertex("C");
        Vertex<String> d = g.addVertex("D");
        Vertex<String> e = g.addVertex("E");

        Edge<String> ab = g.addEdge(a, b, BigRational.of(2));
        Edge<String> ac = g.addEdge(a, c, BigRational.of(5));
        Edge<String> ad = g.addEdge(a, d, BigRational.of(3));
        Edge<String> bc = g.addEdge(b, c, BigRational.of(4));
        Edge<String> ce = g.addEdge(c, e, BigRational.of(1));
        Edge<String> de = g.addEdge(d, e, BigRational.of(4));

        System.out.println(g);
        Dijkstra<String> dijkstra = new Dijkstra<>(g, a);
        Dijkstra<String>.Result shortestPaths = dijkstra.findShortestPaths();
        System.out.println(shortestPaths);
        System.out.println(shortestPaths.getShortestPathVertices(e));
        System.out.println(shortestPaths.getShortestPathEdges(e));
    }
}
