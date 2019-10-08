package org.ump;

import java.util.List;

public class SimpleGraphLib {
    public static void main(String[] args){

        SimpleGraph<String> graph = new SimpleGraph<>(true);
        int testSize = 10;
        String[] vertices = new String[testSize];
        for(int i = 0;i<testSize ; i++){
            vertices[i] = "v"+String.valueOf(i);
            graph.addVertex(vertices[i]);
            for(int j = 0; j<i; j++){
                if(i< j+3 ){
                    graph.addEdge(vertices[i],vertices[j]);
                    if(j%2==0){
                        graph.addEdge(vertices[j],vertices[i]);
                    }
                }
            }
        }
        /*for(int i = 0;i<testSize ; i++){
            for(int j = 0; j<testSize ; j++){*/
                List<Edge<String>> path = graph.getPath(vertices[0],vertices[4]);
                System.out.print(0 + " " + 4 + " : " + vertices[0]);
                for(Edge<String> edge : path){
                    System.out.print("->" + edge.getTo());
                }
                System.out.println("");
        /*    }
        }*/
    }
}
