package org.ump;

import java.util.*;

public abstract class Graph<T> implements IGraph<T> {

    List<T> vertices;
    List<List<Edge<T>>> edges;

    private int countVertices;

    Graph() {

        countVertices = 0;
        vertices = new ArrayList<>();
        edges = new ArrayList<>();

    }

    public void addVertex(T vertex) {
        if (findVertexIndex(vertex) != -1) {

            vertices.add(vertex);
            edges.add(new ArrayList<>());
            countVertices++;

        }
    }

    public final void addEdge(Edge<T> edge) throws ExceptionVertexNotFound {

        T from = edge.getFrom();
        int indexFrom = findVertexIndex(from);
        if (indexFrom == -1)
            throw new ExceptionVertexNotFound();

        if (findVertexIndex(edge.getTo()) == -1)
            throw new ExceptionVertexNotFound();

        List<Edge<T>> edgeList = edges.get(indexFrom);
        edgeList.add(edge);
        edges.set(indexFrom, edgeList);
    }

    public void addEdge(T from, T to) throws ExceptionVertexNotFound {
        addDirectedEdge(from, to);
        addDirectedEdge(to, from);
    }

    private void addDirectedEdge(T from, T to) throws ExceptionVertexNotFound {
        Edge<T> edge = new Edge<>(from, to);
        addEdge(edge);
    }

    public abstract List<Edge<T>> getPath(T from, T to) throws ExceptionVertexNotFound;

    List<Edge<T>> rebuildPath(int[] parent, int[] parentEdge, int index, int indexStart) {
        List<Edge<T>> path = new ArrayList<>();
        boolean[] discovered = new boolean[countVertices];

        discovered[index] = true;
        discovered[indexStart] = true;

        while (parent[index] != -1) {
            path.add(edges.get(parent[index]).get(parentEdge[index]));
            index = parent[index];

            if (discovered[index]) break;
            discovered[index] = true;
        }
        Collections.reverse(path);
        return path;
    }

    protected int findVertexIndex(T vertex) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertex.equals(vertices.get(i))) {
                return i;
            }
        }
        return -1;
    }

    int getCountVertices() {
        return countVertices;
    }

    String pathToString(List<Edge<T>> path, String delimiter) {
        StringBuilder stringMaker = new StringBuilder();
        if (path != null) {
            for (Edge<T> edge : path) {
                stringMaker.append(' ');
                stringMaker.append(edge.getFrom().toString());
                stringMaker.append(delimiter);
                stringMaker.append(edge.getTo().toString());
            }
        }
        return stringMaker.toString();
    }

}


