package org.ump;

import java.util.*;

public class SimpleGraph<T> {
    Set<Vertex<T>> vertexes = new HashSet<Vertex<T>>();
    boolean directed = false;

    public SimpleGraph(){
        directed = false;
    }

    public SimpleGraph(Boolean directed){
        this.directed = directed;
    }

    public void addVertex(Vertex<T> v){
        vertexes.add(v);
    }

    public void addEdge(Vertex a, Vertex b){
        addDirEdge(a,b);
        if(!directed){// if graph not directed lets add back edge
            addDirEdge(b,a);
        }
    }

    private void addDirEdge(Vertex a, Vertex b){
        a.linkVertex(b,1.0);
    }

    public List<Edge> getPath(Vertex a, Vertex b){
        // check a and b

        List<Edge> path = new ArrayList<Edge>();
        // there is we will implement copy Dejkstra
        // without checking optimal weights but only moving
        // But what is the difference between BFS?
        Map<Vertex<T>, Double> length = new HashMap<Vertex<T>, Double>();
        Vertex<T> cur = a;
        while(!length.containsKey(cur)){
            for(Edge edge : cur.getEdges()){
                if(!length.containsKey(edge.next)){
                    length.put(edge.getNext(), edge.getWeight());
                }
            }
        }
        return path;
    }

}
