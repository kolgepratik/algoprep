class Stack {
    constructor () {
        this.data = []; 
    } 

    isEmpty () {
        if (this.data.length < 1) {
           return true; 
        } else {
            return false; 
        }
    } 

    size () {
        return this.data.length; 
    }

    push (data) {
        this.data.push (data); 
    } 

    pop () {
        if (this.isEmpty()) {
            console.error('pop: Stack is empty');
            return undefined; 
        } else {
            var last = this.data[this.data.length - 1]; 
            this.data.splice(this.data.length - 1, 1); 
            return last; 
        }
    } 
} 

class Queue {
    constructor () {
        this.data = []; 
    } 

    isEmpty () {
        if (this.data.length < 1) {
           return true; 
        } else {
            return false; 
        }
    } 

    size () {
        return this.data.length; 
    }

    add (data) {
        this.data.push (data); 
    } 

    remove () {
        if (this.isEmpty()) {
            console.error('remove: Queue is empty');
            return undefined; 
        } else {
            var first = this.data[0]; 
            this.data.splice(0, 1); 
            return first; 
        }
    } 
} 

class MinStack {
    constructor () {
        this.data = []; 
        this.minData = []; 
    } 

    isEmpty () {
        if (this.data.length < 1) {
           return true; 
        } else {
            return false; 
        }
    } 

    size () {
        return this.data.length; 
    }

    push (data) {
        var currentMin = this.minData[this.minData.length - 1]; 
        this.minData.push ((currentMin && data > currentMin) ? currentMin : data);
        this.data.push (data); 
    } 

    popMin () {
        if (this.isEmpty()) {
            console.error('popMin: Stack is empty');
            return undefined; 
        } else {
            var last = this.minData[this.minData.length - 1]; 
            this.minData.splice(this.minData.length - 1, 1); 
            this.data.splice(this.data.length - 1, 1); 
            return last; 
        }
    } 
} 

var st = new Stack(); 
st.push(1);
st.push(2);
st.push(3);
st.push(4); 

console.log('pop: %s', st.pop());
console.log('size: %s', st.size()); 

var q = new Queue(); 
q.add(1);
q.add(2);
q.add(3);
q.add(4); 

console.log('remove: %s', q.remove()); 
console.log('size: %s', q.size()); 

var mst = new MinStack(); 
mst.push(31);
mst.push(14);
mst.push(2);
mst.push(7); 

console.log('popMin: %s', mst.popMin());

module.exports = {
    Stack: Stack, Queue: Queue    
}; 