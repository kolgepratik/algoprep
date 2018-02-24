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

module.exports = {
    Stack: Stack, Queue: Queue    
}; 