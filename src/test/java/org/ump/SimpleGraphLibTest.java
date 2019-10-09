package org.ump;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SimpleGraphLibTest {

    @Test
    public void testDirectedPath() throws ExceptionVertexNotFound {
        SimpleGraph<String> graph = new SimpleGraph<>(true);
        int testSize = 10;
        String[] vertices = new String[testSize];
        for (int i = 0; i < testSize; i++) {
            vertices[i] = "v" + String.valueOf(i);
            graph.addVertex(vertices[i]);
            for (int j = 0; j < i; j++) {
                if (i < j + 3) {
                    graph.addEdge(vertices[i], vertices[j]);
                    if (j % 2 == 0) {
                        graph.addEdge(vertices[j], vertices[i]);
                    }
                }
            }
        }

        String result = graph.pathToString(graph.getPath(vertices[0], vertices[9]), "->");
        assertEquals(" v0->v2 v2->v4 v4->v6 v6->v8 v8->v9", result);
        result = graph.pathToString(graph.getPath(vertices[9], vertices[0]), "->");
        assertEquals(" v9->v7 v7->v5 v5->v3 v3->v1 v1->v0", result);
        graph.addEdge(vertices[0], vertices[9]);
        result = graph.pathToString(graph.getPath(vertices[0], vertices[9]), "->");
        assertEquals(" v0->v9", result);
        result = graph.pathToString(graph.getPath(vertices[9], vertices[0]), "->");
        assertEquals(" v9->v7 v7->v5 v5->v3 v3->v1 v1->v0", result);
        graph.addEdge(vertices[7], vertices[0]);
        result = graph.pathToString(graph.getPath(vertices[9], vertices[0]), "->");
        assertEquals(" v9->v7 v7->v0", result);

    }

    @Test
    public void testUndirecrtedPath() throws ExceptionVertexNotFound {
        SimpleGraph<String> graph = new SimpleGraph<>();
        int testSize = 10;
        String[] vertices = new String[testSize];
        for (int i = 0; i < testSize; i++) {
            vertices[i] = "v" + String.valueOf(i);
            graph.addVertex(vertices[i]);
            for (int j = 0; j < i; j++) {
                if (i < j + 3) {
                    graph.addEdge(vertices[i], vertices[j]);
                    if (j % 2 == 0) {
                        graph.addEdge(vertices[j], vertices[i]);
                    }
                }
            }
        }

        String result = graph.pathToString(graph.getPath(vertices[0], vertices[9]), "->");
        assertEquals(" v0->v1 v1->v3 v3->v5 v5->v7 v7->v9", result);
        result = graph.pathToString(graph.getPath(vertices[9], vertices[0]), "->");
        assertEquals(" v9->v7 v7->v5 v5->v3 v3->v1 v1->v0", result);
        graph.addEdge(vertices[0], vertices[9]);
        result = graph.pathToString(graph.getPath(vertices[0], vertices[9]), "->");
        assertEquals(" v0->v9", result);
        result = graph.pathToString(graph.getPath(vertices[9], vertices[0]), "->");
        assertEquals(" v9->v0", result);
    }

    @Test
    public void testMissingVertex() throws ExceptionVertexNotFound {
        SimpleGraph<String> graph = new SimpleGraph<>();
        int testSize = 10;
        String[] vertices = new String[testSize];
        for (int i = 0; i < testSize; i++) {
            vertices[i] = "v" + String.valueOf(i);
            graph.addVertex(vertices[i]);
            for (int j = 0; j < i; j++) {
                if (i < j + 3) {
                    graph.addEdge(vertices[i], vertices[j]);
                    if (j % 2 == 0) {
                        graph.addEdge(vertices[j], vertices[i]);
                    }
                }
            }
        }
        String result = "";
        try {
            result = graph.pathToString(graph.getPath(vertices[0], "v1000"), "->");
        } catch (ExceptionVertexNotFound e) {
            System.out.println("Not found as expected");
        }
        assertEquals("", result);
        try {
            graph.addEdge(vertices[0], "v1000");
        } catch (ExceptionVertexNotFound e) {
            System.out.println("Not found as expected");
        }
        result = graph.pathToString(graph.getPath(vertices[0], "v3"), ":");
        assertEquals(" v0:v1 v1:v3", result);
        try {
            result = "";
            result = graph.pathToString(graph.getPath(vertices[0], "v1000"), ":");
        } catch (ExceptionVertexNotFound e) {
            System.out.println("Not found as expected");
        }
        assertEquals("", result);
        graph.addVertex("v1000");
        result = graph.pathToString(graph.getPath(vertices[0], "v1000"), ":");
        assertEquals("", result);
        graph.addEdge(vertices[0], "v1000");
        result = graph.pathToString(graph.getPath(vertices[0], "v1000"), "/");
        assertEquals(" v0/v1000", result);

    }

    @Test
    public void test100000() throws ExceptionVertexNotFound {

        SimpleGraph<String> graph = new SimpleGraph<>(true);
        int testSize = 100000;
        String[] vertices = new String[testSize];
        for (int i = 0; i < testSize; i++) {
            vertices[i] = "v" + String.valueOf(i);
            graph.addVertex(vertices[i]);
            for (int j = 0; j < i; j++) {
                if (i < j + 3) {
                    graph.addEdge(vertices[i], vertices[j]);
                    if (j % 2 == 0) {
                        graph.addEdge(vertices[j], vertices[i]);
                    }
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            String textPath = graph.pathToString(graph.getPath(vertices[i], vertices[graph.getCountVertices() - 1 - i]), "->");
        }
        assertTrue(true);
    }


}