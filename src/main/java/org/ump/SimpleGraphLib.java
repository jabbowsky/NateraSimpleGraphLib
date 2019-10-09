package org.ump;

import java.util.List;

public class SimpleGraphLib {
    public static void main(String[] args) {

        SimpleGraph<String> graph = new SimpleGraph<>(true);
        int testSize = 10;
        String[] vertices = new String[testSize];
        for (int i = 0; i < testSize; i++) {
            vertices[i] = "v" + String.valueOf(i);
            graph.addVertex(vertices[i]);
            for (int j = 0; j < i; j++) {
                if (i < j + 3) {
                    try {
                        graph.addEdge(vertices[i], vertices[j]);
                    } catch (ExceptionVertexNotFound exceptionVertexNotFound) {
                        exceptionVertexNotFound.printStackTrace();
                    }
                    if (j % 2 == 0) {
                        try {
                            graph.addEdge(vertices[j], vertices[i]);
                        } catch (ExceptionVertexNotFound exceptionVertexNotFound) {
                            exceptionVertexNotFound.printStackTrace();
                        }
                    }
                }
            }
        }

        try {
            graph.addEdge(vertices[0], vertices[9]);
        } catch (ExceptionVertexNotFound exceptionVertexNotFound) {
            exceptionVertexNotFound.printStackTrace();
        }

        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < testSize; j++) {
                List<Edge<String>> path = null;
                try {
                    path = graph.getPath(vertices[i], vertices[j]);
                } catch (ExceptionVertexNotFound exceptionVertexNotFound) {
                    exceptionVertexNotFound.printStackTrace();
                }
                System.out.print(i + " " + j + " : " + vertices[i]);
                for (Edge<String> edge : path) {
                    System.out.print("->" + edge.getTo());
                }
                System.out.println("");
            }
        }
    }
}
