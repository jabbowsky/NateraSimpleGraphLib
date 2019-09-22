package org.ump;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
    T val;
    List<Edge> edges = new ArrayList<Edge>();

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    public void linkVertex(Vertex next, double weight){
        edges.add(new Edge(next, weight));
    }

}
