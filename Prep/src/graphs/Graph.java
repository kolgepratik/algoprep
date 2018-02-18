package graphsandtrees;

import java.util.LinkedList;

public class Graph {
	private int numberOfVertices;
	private LinkedList<Integer>[] adj; // Adjacency list representation

	/**
	 * Constructor
	 * 
	 * @param numberOfVertices
	 */
	public Graph(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;

		adj = new LinkedList[numberOfVertices];

		for (int v = 0; v < numberOfVertices; v++) {
			adj[v] = new LinkedList<Integer>();
		}
	}

	/**
	 * Add an edge between vertices from and to
	 * 
	 * @param from
	 * @param to
	 */
	public void addEdge(int fromVertex, int toVertex) {
		if (0 <= fromVertex && fromVertex < this.numberOfVertices
				&& 0 <= toVertex && toVertex < this.numberOfVertices) {
			adj[fromVertex].add(toVertex);
		} else {
			System.err
					.println("Invalid vertex specified. Vertex must be between 0 and "
							+ this.numberOfVertices);
		}
	}

	public int getNumberOfVertices() {
		return numberOfVertices;
	}

	public void setNumberOfVertices(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
	}

	public LinkedList<Integer>[] getAdj() {
		return adj;
	}

	public void setAdj(LinkedList<Integer>[] adj) {
		this.adj = adj;
	}
}
