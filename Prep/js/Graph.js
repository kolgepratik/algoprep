var StackQueue = require('./StackQueue.js');
var UnionFind = require('./UnionFind.js');

class Edge {
	constructor (from, to, weight) {
		this.from = from; 
		this.to = to; 
		this.weight = weight === undefined ? 1 : weight; 
	} 
	
	static compareWeights (a, b) {
		return (a.weight < b.weight) ? -1 : ((a.weight === b.weight) ? 0 : 1);
	}
}

class DirectedGraph {
    constructor (vertices, directed) { 
		this.directed = (directed === undefined) ? false : directed; 
		this.vertices = vertices; 
		
		// Adjacency List representation 
        this.adj = []; 
        for (var i=0; i<vertices; i++) {
        	this.adj[i] = []; 
        } 
		
		// Edges list 
		this.edges = []; 

		// Adjacency Matrix representation 
		this.edgesMatrix = []; 
		for (var i=0; i<vertices; i++) {
			this.edgesMatrix[i] = []; 
			for (var j=0; j<vertices; j++) {
				this.edgesMatrix[i][j] = -1; 
			}
        } 
    } 
    
    addEdge (from, to, weight) {
		this.adj[from].push(to);  
		this.edges.push (new Edge(from, to, weight)); 
		this.edgesMatrix[from][to] = weight === undefined ? 1 : weight; 
		this.edgesMatrix[to][from] = weight === undefined ? 1 : weight; 
    } 
    
    removeEdge (from, to) {
    	var idx = this.adj[from].indexOf(to); 
		this.adj[from].splice(idx, 1); 
		
    	this.edges = this.edges.filter (function (e) {
    		return (e.from !== from && e.to !== to); 
		}); 
		
		this.edgesMatrix[from][to] = -1; 
		this.edgesMatrix[to][from] = -1; 
    } 
    
    v () {
    	return this.vertices; 
    } 
    
    e () {
    	return this.edges; 
	} 
	
	getEdge (from, to) {
		for (var i=0; i<this.edges.length; i++) {
			if ((this.edges[i].from === from && this.edges[i].to === to) || (this.edges[i].to === from && this.edges[i].from === to)) {
				return this.edges[i]; 
			}
		}
	}
    
    getAdj (vertex) {
    	return this.adj[vertex]; 
    } 
    
    dfs (vertex) {
    	console.log('dfs: traversal');
    	var visited = []; 
    	for (var i=0; i<this.v(); i++) {
    		visited.push(false); 
    	} 
    	
    	this.dfs_helper (vertex, visited);     	
    } 
    
    dfs_helper (vertex, visited) {
    	visited[vertex] = true; 
    	console.log (vertex);
    	
    	var adj = this.getAdj(vertex); 
    	for (var i=0; i<adj.length; i++) { 
    		var nextVertex = adj[i]; 
    		if (visited[nextVertex] !== true) {
    			this.dfs_helper (nextVertex, visited); 
    		}
    	}
    } 
    
    dfsCycle () {
    	console.log('dfsCycle: detecting');
    	var visited = []; 
    	var st = []; 
    	for (var i=0; i<this.v(); i++) {
    		visited.push(false); 
    		st.push(false); 
    	} 
    	
    	for (var i=0; i<this.v(); i++) {
    		if (this.dfsCycle_helper (i, visited, st)) {
    			return true; 
    		} 
    	}   
    	
    	return false; 
    } 
    
    dfsCycle_helper (vertex, visited, st) {
		if (visited[vertex] === false) {
			st[vertex] = true; 
			visited[vertex] = true; 
			
			var adj = this.getAdj(vertex); 
			for (var i=0; i<adj.length; i++) { 
				var nextVertex = adj[i]; 
				if (visited[nextVertex] === false) {
					if (this.dfsCycle_helper (nextVertex, visited, st)) {
						return true; 
					} 
				} else if (st[nextVertex] === true) {
					return true; 
				}
			} 
		}
		
    	st[vertex] = false; 
    	return false; 
    } 
    
    bfs (vertex) {
    	console.log('bfs: traversal');
    	var q = new StackQueue.Queue(); 
    	var visited = []; 
    	for (var i=0; i<this.v(); i++) {
    		visited.push(false); 
    	} 
    	
    	q.add(vertex); 
    	visited[vertex] = true; 
    	
    	while (!q.isEmpty()) {
    		var nextVertex = q.remove(); 
    		console.log(nextVertex);
    		
	    	var adj = this.getAdj(nextVertex); 
	    	for (var i=0; i<adj.length; i++) { 
	    		var next = adj[i]; 
	    		if (visited[next] !== true) {
	    			visited[next] = true; 
	    			q.add (next); 
	    		}
	    	} 
    	}     	
    }  
    
    topologicalSort () {
    	console.log('topologicalSort: traversal');
    	var st = new StackQueue.Stack(); 
    	var visited = []; 
    	for (var i=0; i<this.v(); i++) {
    		visited.push(false); 
    	} 
    	
    	for (var i=0; i<this.v(); i++) {
    		if (visited[i] !== true) {
    			this.topologicalSort_helper (i, visited, st); 
    		}
    	} 
    	
    	while (!st.isEmpty()) {
    		console.log(st.pop()); 
    	}
    } 
    
    topologicalSort_helper (vertex, visited, st) {
    	visited[vertex] = true; 
    	
    	var adj = this.getAdj(vertex); 
    	for (var i=0; i<adj.length; i++) { 
    		var next = adj[i]; 
    		if (visited[next] !== true) {
    			this.topologicalSort_helper (next, visited, st); 
    		}
    	} 
    	
    	st.push(vertex); 
    } 
    
    kruskalMST () { 
		console.log('kruskalMST: traversing'); 

		var mstEdges = []; 
		var edges = this.e(); 

		var vertices = this.v(); 
		var verticesArray = []; 
		for (var i=0; i<vertices; i++) {
			verticesArray[i] = i; 
		}
		var ufr = new UnionFind.UnionFindByRank(verticesArray); 

		edges.sort (Edge.compareWeights); 
		
		var i = 0; 
		while (mstEdges.length < vertices - 1) { 
			var edge = edges[i++]; 

			var fromParent = ufr.find  (edge.from); 
			var toParent = ufr.find  (edge.to); 

			if (fromParent !== toParent) {
				mstEdges.push (edge); 
				ufr.union (fromParent, toParent); 
			} 
		} 

		for (var i=0; i<mstEdges.length; i++) {
			console.log(mstEdges[i]);
		}
	} 
	
	unionFindCycle () { 
		var vertices = this.v(); 
		var edges = this.e(); 

		var verticesArray = []; 
		for (var i=0; i<vertices; i++) {
			verticesArray[i] = i; 
		}

		var ufr = new UnionFind.UnionFindByRank(verticesArray); 

		for (var i=0; i<edges.length; i++) { 
			var edge = edges[i]; 

			var fromParent = ufr.find (edge.from); 
			var toParent = ufr.find (edge.to); 

			if (fromParent === toParent) {
				return true; 
			} else {
				ufr.union (fromParent, toParent); 
			}
		} 

		return false; 
	} 

	primMST () {
		console.log('primMST: traversing'); 
		
		var edges = this.e(); 

		var vertices = this.v(); 
		var verticesWeight = []; 
		var verticesArray = []; 
		var verticesParentArray = []; 
		for (var i=0; i<vertices; i++) {
			verticesWeight[i] = Number.MAX_VALUE; 
			verticesArray[i] = false; 
			verticesParentArray[i] = false; 
		} 

		verticesWeight[0] = true; 
		verticesParentArray[0] = -1; 
		for (var i=0; i< vertices - 1; i++) {
			var nextVertex = this.primMST_getMin (verticesWeight, verticesArray); 

			verticesArray[nextVertex] = true; 

			for (var j=0; j<edges.length; j++) { 
				var e = edges[j]; 
				if (e.from === nextVertex) {
					if (verticesArray[e.to] === false && e.weight < verticesWeight[e.to]) {
						verticesParentArray[e.to] = nextVertex; 
						verticesWeight[e.to] = e.weight; 
					}
				} else if (e.to === nextVertex) { 
					if (verticesArray[e.from] === false && e.weight < verticesWeight[e.from]) {
						verticesParentArray[e.from] = nextVertex; 
						verticesWeight[e.from] = e.weight; 
					}
				} 
			}
		} 

		this.printMST_print (verticesParentArray); 
	} 

	primMST_getMin (array, verticesArray) { 
		var min = Number.MAX_VALUE; 
		var minIdx = -1
		for (var i=0; i<array.length; i++) {
			if (array[i] < min && verticesArray[i] === false) {
				min = array[i]; 
				minIdx = i; 
			}
		} 

		return minIdx; 
	} 

	printMST_print (verticesParentArray) {
		for (var i=1; i<this.v(); i++) {
			var edge = this.getEdge (verticesParentArray[i], i); 
			console.log(edge);
		}
	} 

	dfsPathExists (from, to) { 
		console.log ('findPath: traversing');

		var visited = []; 
    	for (var i=0; i<this.v(); i++) {
    		visited.push(false); 
		} 
		
		return this.dfsPathExists_helper (from, visited, to); 
	} 

	dfsPathExists_helper (fromVertex, visited, toVertex) { 
		if (fromVertex === toVertex) {
			return true; 
		} else {
			// visit this vertex 
			visited[fromVertex] = true; 

			var adj = this.getAdj(fromVertex); 
			for (var i=0; i<adj.length; i++) {
				var next = adj[i]; 

				if (visited[next] === false) {
					if (this.dfsPathExists_helper (next, visited, toVertex) === true) {
						return true; 
					}
				} 
			}
		} 

		return false; 
	} 

	djksShortestPath (vertex) { 
		console.log ('djksShortestPath: traversing');
		var edges = this.e(); 
		var vertices = this.v(); 

		var inset = []; 
		var dist = []; 
		for (var i=0; i<vertices; i++) {
			inset[i] = false; 
			dist[i] = Number.MAX_VALUE; 
		} 

		dist[vertex] = 0; 
		for (var i=0; i<vertices; i++) {
			var next = this.djksShortestPath_getMinIdx (dist, inset); 
			inset[next] = true; 

			for (var a=0; a<vertices; a++) {
				if (this.edgesMatrix[next][a] != -1) { 
					if (dist[a] === Number.MAX_VALUE) {
						dist[a] = dist[next] + this.edgesMatrix[next][a]; 
					} else { 
						if (dist[a] > dist[next] + this.edgesMatrix[next][a]) {
							dist[a] = dist[next] + this.edgesMatrix[next][a]; 
						}
					} 

				}
			} 
		} 

		console.log ('distance from %s: %s', vertex, dist); 
	} 

	djksShortestPath_getMinIdx (array, inset) { 
		var min = Number.MAX_VALUE; 
		var minIdx = -1; 

		for (var i=0; i<array.length; i++) {
			if (array[i] < min && inset[i] === false) {
				minIdx = i; 
				min = array[i]; 
			}
		} 

		return minIdx; 
	} 

	// not working as expected 
	bellmanFord (vertex) { 
		console.log ('bellmanFord: traversing');
		var edges = this.e(); 
		var vertices = this.v(); 

		var dist = []; 
		for (var i=0; i<vertices; i++) {
			dist[i] = Number.MAX_VALUE; 
		} 

		dist[vertex] = 0; 
		for (var i=1; i<vertices; i++) {
			for (var e=0; e<edges.length; e++) {
				var edge = edges[e]; 

				if (dist[edge.from] !== Number.MAX_VALUE) { 
					if (dist[edge.to] > dist[edge.from] + edge.weight) { 
						dist[edge.to] = dist[edge.from] + edge.weight; 
					} 
				}
			}
		} 

		console.log('distance from %s: %s', vertex, dist);
	} 

	flyodWarshall () { 
		console.log ('flyodWarshall: traversing');
		var vertices = this.v(); 

		var dist = []; 
		for (var i=0; i<vertices; i++) { 
			dist[i] = []; 
			for (var j=0; j<vertices; j++) { 
				dist[i][j] = this.edgesMatrix[i][j] === -1 ? Number.MAX_VALUE : this.edgesMatrix[i][j];
			} 
			dist[i][i] = 0; 
		}

		for (var k=0; k<vertices; k++) {
			for (var i=0; i<vertices; i++) {
				for (var j=0; j<vertices; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j]; 
					}
				}
			}
		} 

		console.log('flyodWarshall: shortest paths between all pairs'); 
		for (var i=0; i<vertices; i++) {
			console.log(dist[i]);
		}
	}
    
} 

var dg = new DirectedGraph(6);
dg.addEdge (2, 3);
dg.addEdge (3, 1);
dg.addEdge (4, 0);
dg.addEdge (5, 0);
dg.addEdge (5, 2);

dg.dfs (2); 
dg.bfs (2);
dg.topologicalSort(); 
console.log('dfsCycle: ' + dg.dfsCycle()); 
console.log('unionFindCycle: ' + dg.unionFindCycle()); 

var dg2 = new DirectedGraph(9); 
dg2.addEdge (0, 1, 4);
dg2.addEdge (0, 7, 8);
dg2.addEdge (1, 2, 8);
dg2.addEdge (1, 7, 11);
dg2.addEdge (2, 3, 7);
dg2.addEdge (2, 5, 4);
dg2.addEdge (2, 8, 2);
dg2.addEdge (3, 4, 9);
dg2.addEdge (3, 8, 14);
dg2.addEdge (4, 5, 10);
dg2.addEdge (5, 6, 2);
dg2.addEdge (6, 7, 1);
dg2.addEdge (6, 8, 6);
dg2.addEdge (7, 8, 7); 

dg2.kruskalMST();
dg2.primMST(); 

console.log('path exists: ' + dg.dfsPathExists (5, 0)); 

dg2.djksShortestPath (0);
dg2.bellmanFord(0); 
dg2.flyodWarshall();