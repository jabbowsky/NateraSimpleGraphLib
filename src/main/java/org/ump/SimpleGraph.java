package org.ump;

import java.util.*;

public class SimpleGraph<T> extends Graph<T> {

    Map<T, Integer> indexMap;

    SimpleGraph() {
        this(false);
    }

    SimpleGraph(boolean directed) {
        super(directed);
        indexMap = new HashMap<>();
    }

    public void addVertex(T vertex) {
        indexMap.put(vertex, getCountVertices());
        super.addVertex(vertex);
    }

    @Override
    protected int findVertexIndex(T vertex) {
        return indexMap.getOrDefault(vertex, -1);
    }
}
