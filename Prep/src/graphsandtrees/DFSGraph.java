/**
 * 
 */
package graphsandtrees;

import java.util.Iterator;

public class DFSGraph {

	public void dfs(Graph graph, int startVertex) {
		// By default no vertices are visited
		boolean visitedVertices[] = new boolean[graph.getNumberOfVertices()];

		dfsRecursive(graph, startVertex, visitedVertices);
	}

	/**
	 * Perform Depth-First-Search starting at the given index
	 * 
	 * @param graph
	 * @param startVertex
	 */
	public void dfsRecursive(Graph graph, int currentVertex,
			boolean[] visitedVertices) {
		// Mark the first vertex as visted
		visitedVertices[currentVertex] = true;
		System.out.println("currentVertex: " + currentVertex);

		// Check all the adjacent vertices for the current node and try to
		// visit them if they are not already visited
		Iterator<Integer> adjVerticesIterator = graph.getAdj()[currentVertex]
				.listIterator();
		while (adjVerticesIterator.hasNext()) {
			int nextVertex = adjVerticesIterator.next();
			if (!visitedVertices[nextVertex]) {
				dfsRecursive(graph, nextVertex, visitedVertices);
			}
		}
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DFSGraph bfsGraph = new DFSGraph();

		Graph graph = new Graph(6);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 5);
		graph.addEdge(5, 4);

		bfsGraph.dfs(graph, 0);
	}
}
