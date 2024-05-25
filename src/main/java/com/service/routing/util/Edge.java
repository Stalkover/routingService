package com.service.routing.util;

import com.service.routing.Way;

public class Edge {
	private Vertex start;
	private Vertex end;
	private Integer weight;
	private Way way;

	public Edge(Vertex startV, Vertex endV, Integer inputWeight, Way way) {
		this.start = startV;
		this.end = endV;
		this.weight = inputWeight;
		this.way = way;
	}

	public Vertex getStart() {
		return this.start;
	}
	
	public Vertex getEnd() {
		return this.end;
	}
	
	public Integer getWeight() {
		return this.weight;
	}
}
