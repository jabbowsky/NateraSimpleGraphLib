package org.ump;

import java.util.List;

public interface IGraph<T> {
        void addVertex(T vertex);
        void addEdge(Edge<T> edge);
        List<Edge<T>> getPath(T from, T to);
}
