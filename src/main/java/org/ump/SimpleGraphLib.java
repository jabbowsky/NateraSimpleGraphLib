package org.ump;

import java.util.List;

public class SimpleGraphLib {
    public static void main(String[] args){
        /*
        SimpleGraph g = new SimpleGraph.Builder().setDirected().build();
        int testSize = 10;
        Vertex[] vrtx = new Vertex[testSize];
        for(int i = 0;i<testSize ; i++){
            Vertex<Integer> cur = new Vertex<Integer>(i);
            vrtx[i] = cur;
            g.addVertex(cur);
            for(int j = 0; j<i; j++){
                if(i< j+3 ){
                    g.addEdge(vrtx[i],vrtx[j]);
                    if(j%2==0){
                        g.addEdge(vrtx[j],vrtx[i]);
                    }
                }
            }
        }
        for(int i = 0;i<testSize ; i++){
            for(int j = 0; j<testSize ; j++){
                List<Edge> path = g.getPath(vrtx[i],vrtx[j]);
                System.out.print(i + " " + j + " : " + vrtx[i].val);
                for(Edge e : path){
                    System.out.print("->" + e.next.val);
                }
                System.out.println("");
            }
        }*/
    }
}
