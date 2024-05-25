package com.service.routing.util;

public class LinkedNode {

    public Vertex data;
    private LinkedNode next;

    public LinkedNode(Vertex data) {
        this.data = data;
        this.next = null;
    }

    public void setNextNode(LinkedNode node) {
        this.next = node;
    }

    public LinkedNode getNextNode() {
        return this.next;
    }

}