var StackQueue = require('./StackQueue.js');

class Edge {
	constructor (from, to, weight) {
		this.from = from; 
		this.to = to; 
		this.weight = weight === undefined ? 1 : weight; 
	} 
	
	compareWeights (a, b) {
		return (a.weight < b.weight) ? -1 : ((a.weight === b.weight) ? 0 : 1);
	}
}

class DirectedGraph {
    constructor (vertices) {
        this.vertices = vertices; 
        this.adj = []; 
        for (var i=0; i<vertices; i++) {
        	this.adj[i] = []; 
        } 
        
        this.edges = []; 
    } 
    
    addEdge (from, to, weight) {
    	this.adj[from].push(to);  
    	this.edges.push (new Edge(from, to, weight)); 
    } 
    
    removeEdge (from, to) {
    	var idx = this.adj[from].indexOf(to); 
    	this.adj[from].splice(idx, 1); 
    	this.edges = this.edges.filter (function (e) {
    		return (e.from !== from && e.to !== to); 
    	}); 
    } 
    
    v () {
    	return this.vertices; 
    } 
    
    e () {
    	return this.edges; 
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
    	st[vertex] = true; 
    	visited[vertex] = true; 
    	//console.log (vertex);
    	
    	var adj = this.getAdj(vertex); 
    	for (var i=0; i<adj.length; i++) { 
    		var nextVertex = adj[i]; 
    		if (visited[nextVertex] !== true) {
    			return this.dfsCycle_helper (nextVertex, visited, st); 
    		} else if (st[nextVertex] === true) {
    			return true; 
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
    
    mst () { 
    	var edges = this.e(); 
    	
    	Arrays.sort (edges); 
    	
    }
    
} 


var dg = new DirectedGraph(6);
dg.addEdge (2, 3);
dg.addEdge (3, 1);
dg.addEdge (4, 0);
dg.addEdge (4, 1);
dg.addEdge (5, 0);
dg.addEdge (5, 2);

dg.dfs (2); 
dg.bfs (2);
dg.topologicalSort(); 
console.log('Cycle: ' + dg.dfsCycle()); 