package com.service.routing.util;

import java.util.ArrayList;

class GraphTraverser {

	public static void depthFirstTraversal(Vertex start, ArrayList<Vertex> visitedVertices) {
		System.out.println(start.getData());

		for (Edge e: start.getEdges()) {
			Vertex neighbor = e.getEnd();

			if (!visitedVertices.contains(neighbor)) {
				visitedVertices.add(neighbor);
				GraphTraverser.depthFirstTraversal(neighbor, visitedVertices);
			}
		}
	}

	public static void breadthFirstSearch(Vertex start, ArrayList<Vertex> visitedVertices) {
		Queue visitQueue = new Queue();
		visitQueue.enqueue(start);
		while (!visitQueue.isEmpty()) {
			Vertex current = visitQueue.dequeue();
			System.out.println(current.getData());

			for (Edge e: current.getEdges()) {
				Vertex neighbor = e.getEnd();
				if (!visitedVertices.contains(neighbor)) {
					visitedVertices.add(neighbor);
					visitQueue.enqueue(neighbor);
				}
			}
		}
	}
}