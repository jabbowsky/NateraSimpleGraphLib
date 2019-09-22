package org.ump;

public class Edge {
    Vertex next;
    double weight = 1.0;
    Edge(Vertex next,double weight){
        this.next = next;
        this.weight = weight;
    }

    public Vertex getNext() {
        return next;
    }

    public void setNext(Vertex next) {
        this.next = next;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
