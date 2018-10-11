class UnionFindByRank {
	constructor (data) {
		this.data = data; 
		
		this.parent = data.slice(); 
		this.parent.fill(-1); 

		this.rank = data.slice(); 
		this.rank.fill(0);
	} 

	higherRank (a, b) {
		return (this.rank[a] >= this.rank[b]) ? a : b; 
	}

	find (item) { 
		if (this.parent[item] === -1) { 
			return item; 
		} else {
			return this.find (this.parent[item]); 
		} 
	} 

	union (a, b) { 
		var aRoot = this.find (a); 
		var bRoot = this.find (b); 

		if (this.rank[aRoot] === this.rank[bRoot]) {
			this.parent[bRoot] = aRoot; 
			this.rank[aRoot]++; 
		} else { 
			if (this.higherRank (aRoot, bRoot) === aRoot) {
				this.parent [bRoot] = aRoot; 
			} else {
				this.parent [aRoot] = bRoot; 
			}
		}
	} 
} 

module.exports = {
    UnionFindByRank: UnionFindByRank 
}; 