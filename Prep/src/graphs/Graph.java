package graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	public static final Integer INIT_CAPACITY = 10;
	public static final Integer CAPACITY_FACTOR = 2;

	public List<List<Integer>> adj; // Adj list representation.
	public List<Integer> indeg; // Indegree for every vertex.

	public int v;

	public Graph(int v) {
		this.v = v;
		this.adj = new ArrayList<List<Integer>>();
		this.indeg = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Graph [v=" + this.v + ", adj=" + this.adj.toString() + "]";
	}

	public Integer addVertex() {
		this.adj.add(new LinkedList<Integer>());
		this.indeg.add(0);
		this.v++;

		return this.v;
	}

	private boolean validVertex(int vertex) {
		return (0 <= vertex && vertex < this.adj.size() && this.adj.get(vertex) != null);
	}

	public void addEdge(int from, int to) {
		if (validVertex(from) && validVertex(to)) {
			this.adj.get(from).add(to);
			this.indeg.set(to, this.indeg.get(to) + 1);
		} else {
			System.err
					.println("Invalid vertex specified. Vertex must be between 0 and "
							+ this.adj.size());
		}
	}

	public boolean removeEdge(int from, int to) {
		if (validVertex(from) && validVertex(to)) {
			for (int a = 0; a < this.adj.get(from).size(); a++) {
				if (this.adj.get(from).get(a) == to) {
					this.adj.get(from).remove(a);
					this.indeg.set(to, this.indeg.get(to) - 1);
					return true;
				}
			}
		}

		return false;
	}

	public boolean removeVertex(int vertex) {
		if (validVertex(vertex)) {
			for (int i = 0; i < this.adj.size(); i++) {
				for (int a = 0; a < this.adj.get(i).size(); a++) {
					if (this.adj.get(i).get(a) == vertex) {
						this.adj.get(i).remove(a);
						break;
					}
				}
			}

			for (int a = 0; a < this.adj.get(vertex).size(); a++) {
				this.indeg.set(this.adj.get(vertex).get(a),
						this.indeg.get(this.adj.get(vertex).get(a)) - 1);
			}

			this.adj.set(vertex, null);
			this.indeg.set(vertex, 0);
			this.v--;

			return true;
		}

		return false;
	}

	public void bfs(int start) {
		System.out.println("\nBFS for " + this);
		LinkedList<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[this.adj.size()]; // All is false by
		// default.

		// Add start index.
		q.add(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int current = q.poll(); // First element of queue.
			System.out.print(" " + current);

			// Add all adjacent vertices to q.
			Iterator<Integer> adj = this.adj.get(current).listIterator();
			while (adj.hasNext()) {
				int next = adj.next();
				if (validVertex(next) && !visited[next]) {
					visited[next] = true;
					q.add(next);
				}
			}
		}
	}

	public void dfs(int start) {
		System.out.println("\nDFS for " + this);

		dfs_helper(start, new boolean[this.adj.size()]);
	}

	private void dfs_helper(int current, boolean[] visited) {
		// Visit current index.
		System.out.print(" " + current);

		// Visit each adjacent vertex and its first adjacent vertex.
		Iterator<Integer> adj = this.adj.get(current).listIterator();
		while (adj.hasNext()) {
			int next = adj.next();
			if (validVertex(next) && !visited[next]) {
				visited[next] = true;
				dfs_helper(next, visited);
			}
		}
	}

	public void topologicalSort() {
		System.out.println("\nTopologicalSort for " + this);

		boolean[] visited = new boolean[this.adj.size()];
		LinkedList<Integer> stack = new LinkedList<>();

		for (int i = 0; i < this.adj.size(); i++) {
			visited = topologySort_helper(i, visited, stack);
		}

		while (!stack.isEmpty()) {
			System.out.print(" " + stack.pollLast());
		}
	}

	public boolean[] topologySort_helper(int current, boolean[] visited,
			LinkedList<Integer> stack) {
		if (!visited[current]) {
			visited[current] = true;
			for (Integer next : this.adj.get(current)) {
				if (validVertex(next) && !visited[next]) {
					topologySort_helper(next, visited, stack);
				}
			}
			stack.add(current);
		}

		return visited;
	}

	public void topologicalSort2() {
		LinkedList<Integer> t = new LinkedList<>();
		LinkedList<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[this.adj.size()];
		List<Integer> indegree = new ArrayList<>(this.indeg);

		for (int iv = 0; iv < indegree.size(); iv++) {
			if (indegree.get(iv) == 0) {
				q.add(iv);
			}
		}

		while (!q.isEmpty()) {
			int current = q.poll();
			t.add(current);

			// Add all adjacent vertices to q.
			Iterator<Integer> adj = this.adj.get(current).listIterator();
			while (adj.hasNext()) {
				int next = adj.next();
				indegree.set(next, indegree.get(next) - 1);
				if (validVertex(next) && !visited[next]
						&& indegree.get(next) == 0) {
					visited[next] = true;
					q.add(next);
				}
			}
		}

		System.out.println("\nTopologicalSort using Indegree count for: "
				+ this);
		for (Integer v : t) {
			System.out.print(" " + v);
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph(7);

		g.addVertex();
		g.addVertex();
		g.addVertex();
		g.addVertex();
		g.addVertex();
		g.addVertex();

		g.addEdge(1, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 1);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(5, 0);
		g.addEdge(5, 2);

		g.dfs(5);
		g.bfs(5);
		g.topologicalSort();
		g.topologicalSort2();

		System.out.println("\nIndegree: " + g.indeg.toString());
	}
}
