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
        // so BFS
        // variable with all founded ways
        Map<Vertex<T>, List<Edge>> paths = new HashMap<Vertex<T>, List<Edge> >();
        // all new vertises, still not checked
        List<Vertex<T>> curList = new ArrayList<Vertex<T>>();
        curList.add(a); // add start
        while(curList.size()>0){
            List<Vertex<T>> nextList = new ArrayList<Vertex<T>>();
            for(Vertex<T> vtx : curList){
                for(Edge edge : vtx.getEdges()){
                    if(!paths.containsKey(edge.next)){
                        List<Edge> curPath = paths.get(vtx);
                        curPath.add(edge);
                        if(edge.getNext().equals(b)){
                            return curPath;
                        }
                        paths.put(edge.getNext(),curPath);
                    }
                }
            }
            curList= nextList;
        }
        return path;
    }

}
