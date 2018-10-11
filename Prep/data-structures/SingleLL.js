class SingleLLNode {
    constructor (data, next) {
        this.data = data; 
        this.next = next; 
    } 

    print () {
        console.log ('Node: %s', this.data);
    }
} 

class SingleLL {
    constructor (node) {
        this.root = node; 
    } 

    print () { 
        if (!this.root) {
            console.log('print: List is empty');
        } else {
            console.log('List Start');
            var current = this.root; 
            while (current) {
                console.log ('%s', current.data);
                current = current.next; 
            }
            console.log('List End');
        }
    } 

    size () {
        if (!this.root) {
            console.log('size: List is empty');
        } else {  
            var sizeCounter = 1;              
            var current = this.root; 
            while (current.next && sizeCounter++) {            
                current = current.next; 
            } 
            
            return sizeCounter; 
        } 
    }

    getFirst () {
        if (!this.root) { 
            console.error('getFirst: List is empty');
        } 
        return this.root.data; 
    } 

    getLast () {
        if (!this.root) { 
            console.error('getLast: List is empty');
        } else {
            var current = this.root; 
            while (current.next) {            
                current = current.next; 
            } 
            
            return current.data; 
        }
    } 

    getN (index) {
        if (!this.root) { 
            console.error('getN: List is empty');
        } else {
            if (index === 1) {
                return this.getFirst(); 
            } else {
                var current = this.root; 
                while (current && index-- > 1) {            
                    current = current.next; 
                } 

                if (!current) {
                    console.error('getN: Not enought elements in List');
                } 
                    
                return current ? current.data : undefined; 
            }
        }
    }

    addLast (data) {
        if (!this.root) {
            this.root = new SingleLLNode(data); 
        } else {                
            var current = this.root; 
            while (current.next) {            
                current = current.next; 
            } 
            current.next = new SingleLLNode(data); 
        } 
    } 

    addFirst (data) {
        if (!this.root) { 
            this.root = new SingleLLNode(data); 
        } else { 
            var node = new SingleLLNode(data); 
            node.next = this.root; 
            this.root = node; 
        } 
    } 

    addN (index, data) {
        if (!this.root) {
            console.error('addN: List is Empty');
        } else { 
            if (index === 1) {
                this.addFirst (data); 
            } else {
                var current = this.root; 
                while (current && --index > 1) {            
                    current = current.next; 
                } 

                if (current) {
                    var node = new SingleLLNode(data); 
                    node.next = current.next; 
                    current.next = node; 
                } else {
                    console.error('addN: Not enough elements in List');
                }
            }
        }
    } 

    deleteFirst () {
        if (!this.root) {
            console.error('deleteFirst: List is Empty');
        } else { 
            this.root = this.root.next; 
        }
    } 

    deleteLast () {
        if (!this.root) {
            console.error('deleteLast: List is Empty');
        } else { 
            if (!this.root.next) {
                this.deleteFirst ();
            } else {
                var prev = this.root; 
                var current = this.root.next; 
                while (current.next) {        
                    prev = current; 
                    current = current.next; 

                } 
                prev.next = undefined; 
            } 
        }
    } 

    deleteN (index) {
        if (!this.root) {
            console.error('deleteN: List is Empty');
        } else { 
            if (index == 1) {
                this.deleteFirst ();
            } else {
                var current = this.root; 
                while (current && --index > 1) {            
                    current = current.next; 
                } 

                if (current) {          
                    current.next = current.next ? current.next.next : undefined; 
                } else {
                    console.error('addN: Not enough elements in List');
                }
            } 
        }
    } 

    hasCycle () {
        if (!this.root) {
            console.error('hasCycle: List is Empty');
        } else { 
            var slowPtr = this.root; 
            var fastPtr = this.root; 

            do {
                slowPtr = slowPtr.next; 
                fastPtr = (fastPtr.next) ? fastPtr.next.next : undefined; 
            } while (slowPtr && fastPtr && !Object.is(slowPtr, fastPtr));  

            if (slowPtr && fastPtr && Object.is(slowPtr, fastPtr)) {
                console.log('hasCycle: Cycle Detected.');
            } else {
                console.log('hasCycle: No Cycle Detected.');
            } 
        } 
    } 

    findCycleStart () {
        if (!this.root) {
            console.error('findCycleStart: List is Empty');
        } else { 
            var slowPtr = this.root; 
            var fastPtr = this.root; 

            do {
                slowPtr = slowPtr.next; 
                fastPtr = (fastPtr.next) ? fastPtr.next.next : undefined; 
            } while (slowPtr && fastPtr && !Object.is(slowPtr, fastPtr));  

            if (slowPtr && fastPtr && Object.is(slowPtr, fastPtr)) {
                console.log('findCycleStart: Cycle Detected.'); 
                
                slowPtr = this.root; 
                while (!Object.is(slowPtr, fastPtr)) {
                    slowPtr = slowPtr.next; 
                    fastPtr = fastPtr.next; 
                } 

                console.log('findCycleStart: Cycle found at: %s', slowPtr.data);
            } else {
                console.log('findCycleStart: No Cycle Detected.');
            } 
        } 
    } 

    findCycleLength () {
        if (!this.root) {
            console.error('findCycleLength: List is Empty');
        } else { 
            var slowPtr = this.root; 
            var fastPtr = this.root; 

            do {
                slowPtr = slowPtr.next; 
                fastPtr = (fastPtr.next) ? fastPtr.next.next : undefined; 
            } while (slowPtr && fastPtr && !Object.is(slowPtr, fastPtr));  

            if (slowPtr && fastPtr && Object.is(slowPtr, fastPtr)) {
                console.log('findCycleLength: Cycle Detected.'); 
                
                var loopCounter = 0; 
                do {
                    fastPtr = fastPtr.next; 
                    loopCounter++; 
                } while (!Object.is(slowPtr, fastPtr)); 

                console.log('findCycleLength: Cycle length: %s', loopCounter);
            } else {
                console.log('findCycleLength: No Cycle Detected.');
            } 
        } 
    } 

    reverseRC () {
        if (!this.root) {
            console.log('reverseRC: List is empty');
        } else {
            this.reverseRC_helper(this.root);
        } 
    }

    reverseRC_helper (node) {
        if (!node.next) {
            this.root = node; 
            return node; 
        } else { 
            var last = this.reverseRC_helper (node.next); 
            node.next = undefined; 
            last.next = node; 
            return node; 
        }
    }
} 

class StackSingleLL {
    constructor () {
        this.list = new SingleLL(); 
    } 

    push (data) {
        this.list.addLast(data); 
    } 

    pop () { 
        if (!this.list.root) {
            console.error('pop: Stack is empty'); 
            return undefined; 
        } else { 
            var lastNode = this.list.getLast(); 
            this.list.deleteLast(); 

            return lastNode; 
        }
    }
}

var sll = new SingleLL(); 
sll.addLast(1);
sll.addLast(2);
sll.addLast(3); 
sll.addLast(4); 

sll.reverseRC();
sll.print();


var stack = new StackSingleLL(); 
stack.push(1);
stack.push(2);
stack.pop();
stack.pop();
