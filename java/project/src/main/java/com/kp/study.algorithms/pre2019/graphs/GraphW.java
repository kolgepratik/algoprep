package pre2019.graphs;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author T2590PK
 *
 */
public class GraphW {

	public int v;
	public LinkedList<EdgeW>[] adj;
	public LinkedList<EdgeW> e;

	public GraphW(int v) {
		super();
		this.v = v;
		this.adj = new LinkedList[this.v];
		for (int i = 0; i < this.v; i++) {
			this.adj[i] = new LinkedList<EdgeW>();
		}
		this.e = new LinkedList<EdgeW>();
	}

	public void addEdge(int from, int to, int weight) {
		EdgeW ne = new EdgeW(from, to, weight);
		this.e.add(ne);
		this.adj[from].add(ne);
	}

	public LinkedList<EdgeW> adj(int from) {
		return this.adj[from];
	}

	@Override
	public String toString() {
		return "GraphW [v=" + v + ", adj=" + Arrays.toString(adj) + "]";
	}

	public static GraphW getSampleGraph1() {
		GraphW gw = new GraphW(9);

		gw.addEdge(0, 1, 15);
		gw.addEdge(0, 2, 25);
		gw.addEdge(1, 4, 10);
		gw.addEdge(1, 7, 25);
		gw.addEdge(1, 8, 5);
		gw.addEdge(2, 3, 10);
		gw.addEdge(2, 4, 20);
		gw.addEdge(4, 5, 10);
		gw.addEdge(4, 6, 5);
		gw.addEdge(8, 7, 15);

		return gw;
	}

	public void flyodWarshall() {
		int[][] d = new int[this.v][this.v];

		for (int i = 0; i < this.v; i++) {
			for (int j = 0; j < this.v; j++) {
				d[i][j] = Integer.MAX_VALUE;
			}
			d[i][i] = 0;
		}

		for (EdgeW e : this.e) {
			d[e.from][e.to] = e.weight;
		}

		for (int k = 0; k < this.v; k++) {
			for (int i = 0; i < this.v; i++) {
				for (int j = 0; j < this.v; j++) {
					if (d[i][k] != Integer.MAX_VALUE
							&& d[k][j] != Integer.MAX_VALUE
							&& d[i][j] > d[i][k] + d[k][j]) {
						d[i][j] = d[i][k] + d[k][j];
					}
				}
			}
		}

		System.out.println("\nFlyod-Warshall Shortest Path to all vertices: ");
		for (int i = 0; i < this.v; i++) {
			System.out.println("\n");
			for (int j = 0; j < this.v; j++) {
				System.out.print(" "
						+ (d[i][j] != Integer.MAX_VALUE ? d[i][j] : "INF"));
			}
		}
	}

	public void bellmanFord(int source) {
		int[] d = new int[this.v];

		for (int i = 0; i < this.v; i++) {
			d[i] = Integer.MAX_VALUE;
		}
		d[0] = 0;

		boolean nochange = false;
		for (int i = 0; i < this.v - 1 && !nochange; i++) {
			nochange = true;
			for (EdgeW e : this.e) {
				int u = e.from;
				int v = e.to;
				int w = e.weight;
				if (d[u] != Integer.MAX_VALUE && d[v] > d[u] + w) {
					d[v] = d[u] + w;
					nochange = false;
				}
			}
		}

		for (EdgeW e : this.e) {
			int u = e.from;
			int v = e.to;
			int w = e.weight;
			if (d[u] != Integer.MAX_VALUE && d[v] > d[u] + w) {
				System.err.println("Graph contains negative weight cycle.");
			}
		}

		System.out.println("\nBellman-Ford Shortest Path to all vertices: \n");

		for (int i = 0; i < this.v; i++) {
			System.out.println(source + " to " + i + " = " + d[i]);
		}
	}

	public void djks(int source) {
		boolean[] in = new boolean[this.v];
		int[] d = new int[this.v];
		int[] p = new int[this.v];

		for (int i = 0; i < this.v; i++) {
			if (i == source) {
				d[i] = 0;
			} else {
				d[i] = Integer.MAX_VALUE;
			}
			p[i] = -1;
		}

		for (int i = 0; i < this.v; i++) {
			int min = -1;
			int minVal = Integer.MAX_VALUE;
			for (int j = 0; j < d.length; j++) {
				if (d[j] < minVal && !in[j]) {
					min = j;
					minVal = d[j];
				}
			}

			in[min] = true;

			for (EdgeW e : this.adj(min)) {
				if (d[min] + e.weight < d[e.to] && !in[e.to]) {
					d[e.to] = d[min] + e.weight;
					p[e.to] = min;
				}
			}
		}

		System.out.println("\nDjks Shortest Path to all vertices: \n");

		for (int i = 0; i < this.v; i++) {
			System.out.println(source + " to " + i + " = " + d[i]
					+ " through: " + p[i]);
		}

	}

	public static void main(String[] args) {
		GraphW gw = GraphW.getSampleGraph1();
		gw.djks(0);
		gw.bellmanFord(0);
		gw.flyodWarshall();
	}

}
