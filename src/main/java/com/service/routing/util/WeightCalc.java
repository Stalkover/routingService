package com.service.routing.util;

import com.service.routing.Way;

public class WeightCalc {
    SpeedMap speedMap = new SpeedMap();
    //default values:
    // priority = 1
    //disInf = 0
    public Integer calc(Vertex vertex1, Vertex vertex2, Way way, double priority, double disInf){
        double maxSpeed = way.getMaxSpeed();
        if(maxSpeed == 0){
            String roadType = way.getHighway();
            maxSpeed = speedMap.getRoadSpeed(roadType);
        }
        double lat1 = vertex1.getNode().getLat();
        double lon1 = vertex1.getNode().getLon();
        double lat2 = vertex2.getNode().getLat();
        double lon2 = vertex2.getNode().getLon();
        double distance = Haversine.haversine(lat1, lon1, lat2, lon2);
        System.out.println(distance / maxSpeed);

        int weight = (int) Math.round((distance / (maxSpeed * priority) + (distance * disInf)) * 100000);
        return weight;
    }
    public Integer calc(Vertex vertex1, Vertex vertex2, Way way){
        double maxSpeed = way.getMaxSpeed();
        if(maxSpeed == 0){
            String roadType = way.getHighway();
            maxSpeed = speedMap.getRoadSpeed(roadType);
        }
        double lat1 = vertex1.getNode().getLat();
        double lon1 = vertex1.getNode().getLon();
        double lat2 = vertex2.getNode().getLat();
        double lon2 = vertex2.getNode().getLon();
        double distance = Haversine.haversine(lat1, lon1, lat2, lon2);

        int weight = (int) Math.round((distance / maxSpeed) * 100000);
        return weight;
    }
}
