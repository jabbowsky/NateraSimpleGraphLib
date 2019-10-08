package org.ump;

import java.lang.reflect.Array;
import java.util.*;

public abstract class Graph<T> implements IGraph<T> {

    protected List<T> vertices;
    protected List<List<Edge<T>>> edges;

    private int countVertices;
    private boolean directed;


    Graph() {
        countVertices = 0;
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    Graph(boolean directed) {
        this();
        this.directed = directed;
    }

    public void addVertex(T vertex) {
        vertices.add(vertex);
        edges.add(new ArrayList<>());
        countVertices++;
    }

    public final void addEdge(Edge<T> edge) {
        T from = edge.getFrom();
        int indexFrom = findVertexIndex(from);
        List<Edge<T>> edgeList = edges.get(indexFrom);
        edgeList.add(edge);
        edges.set(indexFrom, edgeList);
    }

    public final void addEdge(T from, T to) {
        __addEdge(from, to, directed);
    }

    private void __addEdge(T from, T to, boolean addBack) {
        Edge<T> edge = new Edge<>(from, to);
        addEdge(edge);
        if(addBack){
            __addEdge(to, from, false);
        }
    }

    public List<Edge<T>> getPath(T from, T to) {
        int n = vertices.size();

        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] parentEdge = new int[n];
        Arrays.fill(parentEdge, -1);

        int fromIndex = findVertexIndex(from);
        int finalIndex = findVertexIndex(to);
        List<Integer> currentStepVerteces = new ArrayList<>();
        currentStepVerteces.add(fromIndex);

        while (currentStepVerteces.size() > 0) {
            List<Integer> nextStepVerteces = new ArrayList<>();
            for (int parentIndex : currentStepVerteces) {
                List<Edge<T>> edgeList = edges.get(parentIndex);

                for (int edgeId = 0; edgeId < edgeList.size(); edgeId++) {
                    Edge<T> curEdge = edgeList.get(edgeId);
                    int indexTo = findVertexIndex(curEdge.getTo());
                    if (parent[indexTo] == -1) {

                        nextStepVerteces.add(indexTo);

                        parent[indexTo] = parentIndex;
                        parentEdge[indexTo] = edgeId;

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
            path.add(edges.get(index).get(parentEdge[parent[index]]));
            index = parent[index];
            if(discovered[index]){
                break;
            }
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

}
