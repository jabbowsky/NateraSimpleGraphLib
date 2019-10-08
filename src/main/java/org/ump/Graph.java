package org.ump;

import java.util.*;

public abstract class Graph<T> implements IGraph<T> {

    protected List<T> vertices;
    protected List<List<Edge<T>>> edges;

    private int countVertices;

    Graph() {
        countVertices = 0;
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addVertex(T vertex) {
        if( findVertexIndex(vertex) != -1){
            vertices.add(vertex);
            edges.add(new ArrayList<>());
            countVertices++;
        }
    }

    public final void addEdge(Edge<T> edge) {
        T from = edge.getFrom();
        int indexFrom = findVertexIndex(from);
        if (indexFrom == -1) {
            System.err.println("addEdge: Vertex 'from' not founded");
            return;
        }
        if (findVertexIndex(edge.getTo()) == -1) {
            System.err.println("getPath: Vertex 'to' not founded");
            return;
        }
        List<Edge<T>> edgeList = edges.get(indexFrom);
        edgeList.add(edge);
        edges.set(indexFrom, edgeList);
    }

    public void addEdge(T from, T to) {
        addEdgeInt(from, to);
        addEdgeInt(to, from);
    }

    private void addEdgeInt(T from, T to) {
        Edge<T> edge = new Edge<>(from, to);
        addEdge(edge);
    }

    public List<Edge<T>> getPath(T from, T to) {
        int n = vertices.size();

        int fromIndex = findVertexIndex(from);
        if (fromIndex == -1) {
            System.err.println("getPath: Vertex 'from' not founded");
            return null;
        }
        int finalIndex = findVertexIndex(to);
        if (finalIndex == -1) {
            System.err.println("getPath: Vertex 'to' not founded");
            return null;
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] parentEdge = new int[n];
        Arrays.fill(parentEdge, -1);
        List<Integer> currentStepVerteces = new ArrayList<>();
        currentStepVerteces.add(fromIndex);

        while (currentStepVerteces.size() > 0) {
            List<Integer> nextStepVerteces = new ArrayList<>();
            for (int parentIndex : currentStepVerteces) {
                List<Edge<T>> edgeList = edges.get(parentIndex);

                for (int edgeIndex = 0; edgeIndex < edgeList.size(); edgeIndex++) {
                    Edge<T> curEdge = edgeList.get(edgeIndex);
                    int indexTo = findVertexIndex(curEdge.getTo());
                    if (indexTo == -1) {
                        System.err.println("getPath(parentIndex: " + parentIndex + "; edgeIndex: " + edgeIndex + "): Vertex 'to' not founded");
                        continue;
                    }
                    if (parent[indexTo] == -1) {

                        nextStepVerteces.add(indexTo);

                        parent[indexTo] = parentIndex;
                        parentEdge[indexTo] = edgeIndex;

                        if (indexTo == finalIndex) {
                            return rebuildPath(parent, parentEdge, finalIndex, fromIndex);
                        }
                    }
                }
            }
            currentStepVerteces = nextStepVerteces;
        }
        return null;
    }

    ;

    private List<Edge<T>> rebuildPath(int[] parent, int[] parentEdge, int index, int indexStart) {
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

    public int getCountVertices() {
        return countVertices;
    }

    public String pathToString(List<Edge<T>> path, String delimiter) {
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


