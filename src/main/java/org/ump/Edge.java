package org.ump;

public class Edge<T> {

    protected Number weight = 1;
    protected T from;
    protected T to;

    Edge(T from, T to){
        this.from = from;
        this.to = to;
    }

    public Number getWeight() {
        return weight;
    }

    public void setWeight(Number weight) {
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
