class PRQueue { 

    constructor (type = 'min') {
        this.data = []; 
        this.type = type; 
    } 

    size () {
        return this.data.length; 
    }

    isValidIdx (index) {
        return index !== undefined && index >=0 && index < this.data.length; 
    }

    compare (parentIdx, leftChildIdx, rightChildIdx) {
        if (this.type === 'min') { 
            if (this.isValidIdx(leftChildIdx)) {
                var minIdx = leftChildIdx; 
                if (this.isValidIdx(rightChildIdx)) {
                    minIdx = this.get(leftChildIdx) < this.get(rightChildIdx) ? leftChildIdx : rightChildIdx; 
                } 

                return (this.get(parentIdx) < this.get(minIdx)) ? parentIdx : minIdx; 
            } else {
                return parentIdx; 
            }
        } else if (this.type === 'max') {
            if (this.isValidIdx(leftChildIdx)) {
                var maxIdx = leftChildIdx; 
                if (this.isValidIdx(rightChildIdx)) {
                    maxIdx = this.get(leftChildIdx) > this.get(rightChildIdx) ? leftChildIdx : rightChildIdx; 
                } 

                return (this.get(parentIdx) > this.get(minIdx)) ? parentIdx : maxIdx; 
            } else { 
                return parentIdx; 
            }
        } else {
            return parentIdx; 
        }
    }

    isEmpty () {
        return this.data.length < 1; 
    } 

    get (index) {
        return this.isValidIdx(index) ? this.data[index] : undefined; 
    }

    parentIdx (index) {
        if (!this.isValidIdx(index)) {
            return undefined; 
        } else { 
            //console.log('parentIdx: %s = %s', index, Math.ceil(index/2) - 1);
            return Math.ceil(index/2) - 1; 
        }
    }

    parent (index) {
        if (!this.isValidIdx(index)) {
            return undefined; 
        } else {
            return this.get(this.parentIdx(index)); 
        }
    } 

    leftChildIdx (index) {
        if (!this.isValidIdx(index)) {
            return undefined; 
        } else { 
            //console.log('leftChildIdx: %s = %s', index, 2*index + 1);
            return 2*index + 1; 
        }
    }

    leftChild (index) {
        if (!this.isValidIdx(index)) {
            return undefined; 
        } else {
            return this.get(this.leftChildIdx(index)); 
        }
    } 

    rightChildIdx (index) {
        if (!this.isValidIdx(index)) {
            return undefined; 
        } else { 
            //console.log('rightChildIdx: %s = %s', index, 2*index + 2);
            return 2*index + 2; 
        }
    }

    rightChild (index) {
        if (!this.isValidIdx(index)) {
            return undefined; 
        } else {
            return this.get(this.rightChildIdx(index)); 
        }
    } 

    heapifyUp (index) { 
        //console.log('heapifyUp: %s', index);
        var parentIdx = this.parentIdx(index); 
        if (this.isValidIdx(parentIdx)) {
            var leftChildIdx = this.leftChildIdx(parentIdx); 
            var rightChildIdx = this.rightChildIdx(parentIdx); 

            var swapIdx = this.compare (parentIdx, leftChildIdx, rightChildIdx); 
            if (swapIdx === undefined || swapIdx === parentIdx) {
                return; 
            } else if (swapIdx === leftChildIdx) {
                //console.log ('swap left %s with parent %s', leftChildIdx, parentIdx);
                var leftChildData = this.get(leftChildIdx); 
                this.data[leftChildIdx] = this.get(parentIdx); 
                this.data[parentIdx] = leftChildData; 

                this.heapifyUp (parentIdx); 
            } else if (swapIdx === rightChildIdx) {
                //console.log ('swap right %s with parent %s', rightChildIdx, parentIdx);
                var rightChildData = this.get(rightChildIdx); 
                this.data[rightChildIdx] = this.get(parentIdx); 
                this.data[parentIdx] = rightChildData; 

                this.heapifyUp (parentIdx); 
            }             
        }
    } 

    heapifyDown (index) {
        //console.log('heapifyDown: %s', index);
        var parentIdx = index;  
        if (this.isValidIdx(parentIdx)) {
            var leftChildIdx = this.leftChildIdx(parentIdx); 
            var rightChildIdx = this.rightChildIdx(parentIdx); 

            var swapIdx = this.compare (parentIdx, leftChildIdx, rightChildIdx); 
            if (swapIdx === undefined || swapIdx === parentIdx) {
                return; 
            } else if (swapIdx === leftChildIdx) { 
                //console.log ('swap left %s with parent %s', leftChildIdx, parentIdx);
                var leftChildData = this.get(leftChildIdx); 
                this.data[leftChildIdx] = this.get(parentIdx); 
                this.data[parentIdx] = leftChildData; 

                this.heapifyDown (leftChildIdx); 
            } else if (swapIdx === rightChildIdx) { 
                //console.log ('swap right %s with parent %s', rightChildIdx, parentIdx);
                var rightChildData = this.get(rightChildIdx); 
                this.data[rightChildIdx] = this.get(parentIdx); 
                this.data[parentIdx] = rightChildData; 

                this.heapifyDown (rightChildIdx); 
            }             
        }
    } 

    insert (data) {
        //console.log('\ninsert: %s', data);
        if (data === undefined) {
            console.error('insert: data cannot be blank');
        } else {
            this.data[this.data.length] = data; 
            this.heapifyUp (this.data.length - 1); 
        }
        //console.log(this.data);
        //console.log('\n');
    } 

    remove () {
        if (this.isEmpty()) {
            console.error('remove: PRQueue is empty'); 
        } else {
            var data = this.data[0]; 
            this.data[0] = this.data[this.data.length - 1]; 
            this.data.splice(this.data.length - 1, 1); 

            this.heapifyDown (0); 

            return data; 
        }
    } 

    convert () {
        if (this.type === 'min') {
            this.type = 'max'; 
        } else if (this.type === 'max') {
            this.type = 'min'; 
        } 
        console.log('convert: this is now a %s-priority queue', this.type);

        this.heapifyUp(this.data.length - 1); 
    }
} 

/*
var prq = new PRQueue(); 
prq.insert(3);
prq.insert(5);
prq.insert(1);
prq.insert(9);
prq.insert(7);
console.log('%s: %s', prq.type, prq.remove()); 
prq.convert(); 
console.log('%s: %s', prq.type, prq.remove()); 
prq.convert(); 
console.log('%s: %s', prq.type, prq.remove()); 
*/

module.exports = {
    PRQueue: PRQueue 
}; 
