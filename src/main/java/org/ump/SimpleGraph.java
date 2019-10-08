package org.ump;

import java.util.*;

public class SimpleGraph<T> extends Graph<T> {

    Map<T, Integer> indexMap;
    boolean directed;

    SimpleGraph() {
        this(false);
    }

    SimpleGraph(boolean directed) {
        this.directed = directed;
        indexMap = new HashMap<>();
    }

    public void addVertex(T vertex) {
        indexMap.put(vertex, getCountVertices());
        super.addVertex(vertex);
    }

    @Override
    public final void addEdge(T from, T to) {
        addEdgeInt(from, to, !directed);
    }

    private void addEdgeInt(T from, T to, boolean addBack) {
        Edge<T> edge = new Edge<>(from, to);
        addEdge(edge);
        if (addBack) {
            addEdgeInt(to, from, false);
        }
    }

    @Override
    protected int findVertexIndex(T vertex) {
        return indexMap.getOrDefault(vertex, -1);
    }
}
