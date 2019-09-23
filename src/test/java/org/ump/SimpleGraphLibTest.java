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

        SimpleGraph unDirGraph = new SimpleGraph.Builder().build();
        int testSize = 10;
        Vertex[] vrtx = new Vertex[testSize];

        for (int i = 0; i < testSize; i++) {
            Vertex<String> cur = new Vertex<String>(String.valueOf(i));
            vrtx[i] = cur;
            unDirGraph.addVertex(cur);
            for (int j = 0; j < i; j++) {
                if (i < j + 3) {
                    unDirGraph.addEdge(vrtx[i], vrtx[j]);
                }
            }
        }
        List<Edge> path = unDirGraph.getPath(vrtx[5], vrtx[0]);
        String res = "5:0:"+ vrtx[5].val;
        for (Edge e : path) {
            res += "->" + e.next.val;
        }
        assertEquals("5:0:5->3->1->0",res);
        path = unDirGraph.getPath(vrtx[2], vrtx[9]);
        res = "2:9:"+ vrtx[2].val;
        for (Edge e : path) {
            res += "->" + e.next.val;
        }
        assertEquals("2:9:2->3->5->7->9",res);
    }

    @Test
    public void testUndirecrtedPath() {

        SimpleGraph dirGraph = new SimpleGraph.Builder().setDirected().build();
        int testSize = 100;
        Vertex[] vrtx = new Vertex[testSize];

        for (int i = 0; i < testSize; i++) {
            Vertex<String> cur = new Vertex<String>(String.valueOf(i));
            vrtx[i] = cur;
            dirGraph.addVertex(cur);

            if(i>0){
                dirGraph.addEdge(vrtx[i-1], vrtx[i]);
                dirGraph.addEdge(vrtx[i], vrtx[0]);
            }
        }

        List<Edge> path = dirGraph.getPath(vrtx[0], vrtx[99]);
        String res = "0:99:"+ vrtx[0].val;
        for (Edge e : path) {
            res += "->" + e.next.val;
        }
        //System.out.println(res);
        assertEquals("0:99:0->1->2->3->4->5->6->7->8->9->10->11->12->13->14->15->16->17->18->19->20->21->22->23->24->25->26->27->28->29->30->31->32->33->34->35->36->37->38->39->40->41->42->43->44->45->46->47->48->49->50->51->52->53->54->55->56->57->58->59->60->61->62->63->64->65->66->67->68->69->70->71->72->73->74->75->76->77->78->79->80->81->82->83->84->85->86->87->88->89->90->91->92->93->94->95->96->97->98->99",res);
        path = dirGraph.getPath(vrtx[99], vrtx[0]);
        res = "99:0:"+ vrtx[99].val;
        for (Edge e : path) {
            res += "->" + e.next.val;
        }
        assertEquals("99:0:99->0",res);
    }


    @Ignore
    @Test
    public void test10000() {
        SimpleGraph unDirGraph = new SimpleGraph.Builder().build();
        int testSize = 10000;
        Vertex<String>[] vrtx = new Vertex[testSize];
        for (int i = 0; i < testSize; i++) {
            Vertex<String> cur = new Vertex<String>(String.valueOf(i));
            vrtx[i] = cur;
            unDirGraph.addVertex(cur);
            for (int j = 0; j < i; j++) {
                if (i < j + 3) {
                    unDirGraph.addEdge(vrtx[i], vrtx[j]);
                }
            }
        }

        for(int i = 9000;i<9003; i++){
            for(int j = 0; j< 3; j++){
                List<Edge> path = unDirGraph.getPath(vrtx[i],vrtx[j]);
                System.out.print(i + " " + j + " : " + vrtx[i].val);
                for(Edge e : path){
                    System.out.print("->" + e.next.val);
                }
                System.out.println("");
            }
        }

        assertTrue(true);
    }

}