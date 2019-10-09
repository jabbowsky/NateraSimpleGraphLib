package org.ump;

import java.util.*;

public class SimpleGraph<T> extends Graph<T> {

    private Map<T, Integer> indexMap;
    private boolean directed;

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

    public final void addEdge(T from, T to) throws ExceptionVertexNotFound {
        addDirectedEdge(from, to, !directed);
    }

    private void addDirectedEdge(T from, T to, boolean addBack) throws ExceptionVertexNotFound {
        Edge<T> edge = new Edge<>(from, to);
        addEdge(edge);
        if (addBack) {
            addDirectedEdge(to, from, false);
        }
    }

    public List<Edge<T>> getPath(T from, T to) throws ExceptionVertexNotFound {
        int n = getCountVertices();

        int fromIndex = findVertexIndex(from);
        if (fromIndex == -1)
            throw new ExceptionVertexNotFound();

        int finalIndex = findVertexIndex(to);
        if (finalIndex == -1)
            throw new ExceptionVertexNotFound();

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] parentEdge = new int[n];
        Arrays.fill(parentEdge, -1);

        List<Integer> currentStepVertices = new ArrayList<>();
        currentStepVertices.add(fromIndex);

        while (currentStepVertices.size() > 0) {

            List<Integer> nextStepVertices = new ArrayList<>();
            for (int parentIndex : currentStepVertices) {
                List<Edge<T>> edgeList = edges.get(parentIndex);

                for (int edgeIndex = 0; edgeIndex < edgeList.size(); edgeIndex++) {

                    Edge<T> curEdge = edgeList.get(edgeIndex);

                    int indexTo = findVertexIndex(curEdge.getTo());
                    if (indexTo == -1)
                        throw new ExceptionVertexNotFound();

                    if (parent[indexTo] == -1) {

                        nextStepVertices.add(indexTo);

                        parent[indexTo] = parentIndex;
                        parentEdge[indexTo] = edgeIndex;

                        if (indexTo == finalIndex)
                            return rebuildPath(parent, parentEdge, finalIndex, fromIndex);

                    }
                }
            }
            currentStepVertices = nextStepVertices;
        }
        return null;

    }

    protected int findVertexIndex(T vertex) {
        return indexMap.getOrDefault(vertex, -1);
    }
}
