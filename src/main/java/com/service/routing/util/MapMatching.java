package com.service.routing.util;

import java.util.ArrayList;

public class MapMatching {
    public static Vertex match(Graph graph, double lat, double lon){
        ArrayList<Vertex> vertices = graph.getVertices();
        Vertex nearestVertex = null;
        double minDistance = Double.MAX_VALUE;
        for(Vertex vertex : vertices){
            double distance = Haversine.haversine(lat, lon, vertex.getNode().getLat(), vertex.getNode().getLon());
            if (distance < minDistance){
                minDistance = distance;
                nearestVertex = vertex;
            }
        }
        return nearestVertex;
    }
}
