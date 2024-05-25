package com.service.routing;

public class Node {
    long id;
    double lat;
    double lon;

    Node(long id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "Node{id=" + id + ", lat=" + lat + ", lon=" + lon + '}';
    }
}
