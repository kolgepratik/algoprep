package com.kp.study.algorithms.old.graphs;

import java.util.Iterator;

public class DFSGraphCycle {

	public void dfsCycle(Graph graph, int startVertex) {
		// By default no vertices are visited
		boolean visitedVertices[] = new boolean[graph.v];
		boolean stackVertices[] = new boolean[graph.v];

		boolean isCycle = dfsRecursiveCycle(graph, startVertex,
				visitedVertices, stackVertices);

		System.out.println("Graph has cycle: " + isCycle);
	}

	public boolean dfsRecursiveCycle(
			Graph graph, int currentVertex,
			boolean[] visitedVertices, boolean[] stackVertices) {
		// Mark the current vertex as visted and on-stack.
		visitedVertices[currentVertex] = true;
		stackVertices[currentVertex] = true;
		System.out.println("currentVertex: " + currentVertex);

		// Check all the adjacent vertices for the current node and try to
		// visit them if they are not already visited.
		Iterator<Integer> adjVerticesIterator = graph.adj.get(currentVertex)
				.listIterator();
		while (adjVerticesIterator.hasNext()) {
			int nextVertex = adjVerticesIterator.next();

			// If there is a back-edge, report it.
			if (stackVertices[nextVertex]) {
				System.err.println("There is a cycle from " + currentVertex
						+ " to " + nextVertex);
				return true;
			}

			// If the next vertex is not yet visted.
			if (!visitedVertices[nextVertex]) {
				// If there is a cycle found at the next vertex, return true.
				// i.e. terminate recursion.
				if (dfsRecursiveCycle(graph, nextVertex, visitedVertices,
						stackVertices)) {
					return true;
				}
			}
		}

		// Remove current vertex from stack.
		stackVertices[currentVertex] = false;

		// No cycle found yet.
		return false;
	}

	public static void main(String[] args) {
		DFSGraphCycle bfsGraph = new DFSGraphCycle();

		Graph graph = new Graph(7);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(3, 4);
		graph.addEdge(0, 5);
		graph.addEdge(5, 6);
		graph.addEdge(4, 0);

		bfsGraph.dfsCycle(graph, 0);
	}
}
