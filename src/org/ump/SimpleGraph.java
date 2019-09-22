package org.ump;

import java.util.*;

public class SimpleGraph<T> {
    Set<Vertex<T>> vertexes = new HashSet<Vertex<T>>();
    boolean directed = false;

    public static class Builder{

        private boolean directed = false;
        public Builder setDirected() {
            this.directed = true;
            return this;
        }

        public SimpleGraph build(){
            return new SimpleGraph(this);
        }
    }

    SimpleGraph(Builder bld){
        this.directed = bld.directed;
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
        // check a and b not null and not equal
        List<Edge> path = new ArrayList<Edge>();
        if(!vertexes.contains(a)
                || !vertexes.contains(b)
                || a.equals(b)
            ){return path;}

        // there is we will implement copy Dejkstra
        // without checking optimal weights but only moving
        // But what is the difference between BFS?
        // so BFS
        // variable with all founded ways
        Map<Vertex<T>, List<Edge>> paths = new HashMap<Vertex<T>, List<Edge>>();
        // all new vertises, still not checked
        List<Vertex<T>> curList = new ArrayList<Vertex<T>>();
        curList.add(a); // add start
        while(curList.size()>0){
            List<Vertex<T>> nextList = new ArrayList<Vertex<T>>();
            for(Vertex<T> vtx : curList){
                for(Edge edge : vtx.getEdges()){
                    if(!paths.containsKey(edge.next)){
                        nextList.add(edge.next);
                        // we will take all paths from previous points
                        // and add new edge to it.
                        // For first one we will create list
                        List<Edge> curPath;
                        if(paths.get(vtx)!=null){
                            curPath = new ArrayList<Edge>(paths.get(vtx));
                        }else{
                            curPath = new ArrayList<Edge>();
                        }
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
