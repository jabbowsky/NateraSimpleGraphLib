package org.ump;

import java.util.List;

public interface IGraph<T> {

    /*  Add vertex with specified type to existing graph
     *  Vertex has to be uniqe objects
     *  If vertex already exists it will not to be added
     * */
    void addVertex(T vertex);

    /*  Add precreated edge to graph
     *  Each edge contains Objects "From" and "To" whose expected to be on graph
     *  for directed graph edge will be added only to vertex "From"
     *  for undirected will be created two edges and attached to both
     * */
    void addEdge(Edge<T> edge);

    /*  Add edge between two vertices
     *  both vertices has to be exists in praph
     *  Otherwise you will see warning in STDERR
     *  @@ "addEdge: Vertex 'from/to' not founded"
     * */
    void addEdge(T from, T to);

    /*  Find path between two vertices
     *  both vertices has to be exists on graph
     *  otherwise you will see warning
     *  @@ "getPath: Vertex 'to' not founded"
     * */
    List<Edge<T>> getPath(T from, T to);
}
