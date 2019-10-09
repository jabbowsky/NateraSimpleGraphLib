package org.ump;

public class Edge<T> {

    private double weight = 1;
    private T from;
    private T to;

    Edge(T from, T to) {
        this.from = from;
        this.to = to;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public T getFrom() {
        return from;
    }

    public void setFrom(T from) {
        this.from = from;
    }

    public T getTo() {
        return to;
    }

    public void setTo(T to) {
        this.to = to;
    }

}
