package com.service.routing.util;

import java.util.HashMap;

public class SpeedMap {
    HashMap<String, Integer> speedMap;

    public SpeedMap(){
        speedMap = new HashMap<String, Integer>();
        speedMap.put("motorway", 60);
        speedMap.put("trunk", 60);
        speedMap.put("primary", 60);
        speedMap.put("secondary", 60);
        speedMap.put("tertiary", 60);
        speedMap.put("unclassified", 60);
        speedMap.put("residential", 60);
        speedMap.put("motorway_link", 60);
        speedMap.put("trunk_link", 60);
        speedMap.put("primary_link", 60);
        speedMap.put("secondary_link", 60);
        speedMap.put("tertiary_link", 60);
        speedMap.put("living_street", 20);
        speedMap.put("service", 30);
        speedMap.put("pedestrian", 12);
        speedMap.put("cycleway", 12);
        speedMap.put("track", 10);
        speedMap.put("bus_guideway", 10);
        speedMap.put("escape", 10);
        speedMap.put("raceway", 10);
        speedMap.put("road", 60);
        speedMap.put("busway", 10);
        speedMap.put("footway", 5);
        speedMap.put("bridleway", 5);
        speedMap.put("steps", 5);
        speedMap.put("corridor", 5);
        speedMap.put("path", 5);
        speedMap.put("via_ferrata", 5);
    }
    public int getRoadSpeed(String roadtype){
        int speed = speedMap.get(roadtype);
        return speed;
    }
}
