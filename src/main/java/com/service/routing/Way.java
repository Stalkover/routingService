package com.service.routing;

import java.util.ArrayList;
import java.util.List;

public class Way {
    long id;
    List<Long> nodeRefs;
    int maxSpeed;
    String highway;

    Way(long id) {
        this.id = id;
        this.nodeRefs = new ArrayList<>();
    }

    void addNodeRef(long nodeRef) {
        this.nodeRefs.add(nodeRef);
    }

    @Override
    public String toString() {
        return "Way{id=" + id + ", nodeRefs=" + nodeRefs + ", highway=" + highway + ", maxSpeed=" + maxSpeed + '}';
    }

    public String getHighway() {
        return highway;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed){
        this.maxSpeed = maxSpeed;
    }

    public void setHighway(String highway){
        this.highway = highway;
    }
}
