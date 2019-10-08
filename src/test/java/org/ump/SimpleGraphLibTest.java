package org.ump;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class SimpleGraphLibTest {

    @Test
    public void testDirectedPath() {
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
    public void testUndirecrtedPath() {
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
    public void test100000() {

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
            System.out.println(graph.pathToString(graph.getPath(vertices[i], vertices[graph.getCountVertices() - 1 - i]), "->"));
        }
        assertTrue(true);
    }

}