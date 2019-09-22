package org.ump;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.ump.SimpleGraph;

import java.util.List;

public class SimpleGraphLibTest {

    @BeforeClass
    public static void initGraph() {
        System.out.println("This is executed before class for Test");
    }

    @Before
    public void beforeEachTest() {
        System.out.println("This is executed before each Test");
    }

    @After
    public void afterEachTest() {
        System.out.println("This is exceuted after each Test");
    }

    @Test
    public void testDirectedPath() {
        SimpleGraph unDirGraph = new SimpleGraph.Builder().build();
        int testSize = 10;
        Vertex[] vrtx = new Vertex[testSize];

        for (int i = 0; i < testSize; i++) {
            Vertex<Integer> cur = new Vertex<Integer>(i);
            vrtx[i] = cur;
            unDirGraph.addVertex(cur);
            for (int j = 0; j < i; j++) {
                if (i < j + 3) {
                    unDirGraph.addEdge(vrtx[i], vrtx[j]);
                    if (j % 2 == 0) {
                        unDirGraph.addEdge(vrtx[j], vrtx[i]);
                    }
                }
            }
        }
        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < testSize; j++) {
                List<Edge> path = unDirGraph.getPath(vrtx[i], vrtx[j]);
                System.err.print(i + " " + j + " : " + vrtx[i].val);
                for (Edge e : path) {
                    System.out.print("->" + e.next.val);
                }
                System.out.println("");
            }
        }
    }

    /*@Test
    public void testDivison() {
        try {
            int result = calculator.divison(10, 2);

            assertEquals(5, result);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    @Test(expected = Exception.class)
    public void testDivisionException() throws Exception {
        calculator.divison(10, 0);
    }

    @Ignore
    @Test
    public void testEqual() {
        boolean result = calculator.equalIntegers(20, 20);

        assertFalse(result);
    }

    @Ignore
    @Test
    public void testSubstraction() {
        int result = 10 - 3;

        assertTrue(result == 9);
    }*/
}