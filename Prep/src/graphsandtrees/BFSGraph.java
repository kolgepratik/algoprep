/**
 * 
 */
package graphsandtrees;

import java.util.Iterator;
import java.util.LinkedList;

public class BFSGraph {

	/**
	 * Perform Breadth-First-Search starting at the given index
	 * 
	 * @param graph
	 * @param startVertex
	 */
	public void bfs(Graph graph, int startVertex) {
		// By default no vertices are visited
		boolean visitedVertices[] = new boolean[graph.getNumberOfVertices()];

		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the first vertex as visted
		visitedVertices[startVertex] = true;
		queue.add(startVertex);

		// Repeat until the queue is empty
		while (!queue.isEmpty()) {
			int currentVertex = queue.poll();
			System.out.println("currentVertex: " + currentVertex);

			// Check all the adjacent vertices for the current node and try to
			// visit them if they are not already visited
			Iterator<Integer> adjVerticesIterator = graph.getAdj()[currentVertex]
					.listIterator();
			while (adjVerticesIterator.hasNext()) {
				int queueVertex = adjVerticesIterator.next();
				if (!visitedVertices[queueVertex]) {
					visitedVertices[queueVertex] = true;
					queue.add(queueVertex);
				}
			}
		}
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BFSGraph bfsGraph = new BFSGraph();

		Graph graph = new Graph(6);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 5);
		graph.addEdge(5, 4);

		bfsGraph.bfs(graph, 0);
	}
}
