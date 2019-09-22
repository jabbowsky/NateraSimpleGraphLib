package org.ump;

public class SimpleGraphLib {
    public static void main(String[] args){

        SimpleGraph g = new SimpleGraph();
        int testSize = 10;
        Vertex[] vrtx = new Vertex[testSize];
        for(int i = 0;i<testSize ; i++){
            Vertex<Integer> cur = new Vertex<Integer>(i);
            vrtx[i] = cur;
            g.addVertex(cur);
            for(int j = 0; j<i; j++){
                if(j%2==0){
                    g.addEdge(vrtx[i],vrtx[j]);
                }
            }
        }
        for(int i = 0;i<testSize ; i++){
            for(int j = 0; j<i; j++){
                System.out.println(i + " " + j + " : " + g.getPath(vrtx[i],vrtx[j]));
            }
        }
    }
}
