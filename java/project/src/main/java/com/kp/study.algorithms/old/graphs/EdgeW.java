package com.kp.study.algorithms.old.graphs;

public class EdgeW {
	public int from;
	public int to;

	public int weight;

	public EdgeW(int from, int to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "EdgeW [from=" + from + ", to=" + to + ", weight=" + weight
				+ "]";
	}

}
