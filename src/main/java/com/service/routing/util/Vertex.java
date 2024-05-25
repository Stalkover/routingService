package com.service.routing.util;

import com.service.routing.Node;
import com.service.routing.Way;

import java.util.ArrayList;

public class Vertex {

    private String data;
    private ArrayList<Edge> edges;
    private Node node;

    public Vertex(String inputData, Node node) {
        this.data = inputData;
        this.node = node;
        this.edges = new ArrayList<Edge>();
    }
    public Vertex(String inputData){
        this.data = inputData;
        this.edges = new ArrayList<Edge>();
    }

    public void addEdge(Vertex endVertex, Integer weight, Way way) {
        this.edges.add(new Edge(this, endVertex, weight, way));
    }

    public void removeEdge(Vertex endVertex) {
        this.edges.removeIf(edge -> edge.getEnd().equals(endVertex));
    }

    public String getData() {
        return this.data;
    }

    public Node getNode(){
        return this.node;
    }

    public ArrayList<Edge> getEdges(){
        return this.edges;
    }

    public void print(boolean showWeight) {
        String message = "";

        if (this.edges.size() == 0) {
            System.out.println(this.data + " -->");
            return;
        }

        for(int i = 0; i < this.edges.size(); i++) {
            if (i == 0) {
                message += this.edges.get(i).getStart().data + " -->  ";
            }

            message += this.edges.get(i).getEnd().data;

            if (showWeight) {
                message += " (" + this.edges.get(i).getWeight() + ")";
            }

            if (i != this.edges.size() - 1) {
                message += ", ";
            }
        }
        System.out.println(message);
    }
}

