var Util = require('./Common.js'); 
var StackQueue = require('./StackQueue.js'); 
var PRQueue = require('./PRQueue.js'); 
var BTree = require('./BTree.js'); 

var $ = new Util.Util(); 

/* Huffman Coding */ 

class HuffmanNode extends BTree.BTreeNode {
    constructor (data, item) {
        super (data); 
        this.item = item; 
    } 

    valueOf () {
        return this.data; 
    }
}

function huffmanCoding (a, f) { 
    $.log ('huffmanCoding: '); 
    var pq = new PRQueue.PRQueue(); 

    for (var i=0; i<f.length; i++) {
        pq.insert (new HuffmanNode(f[i], a[i])); 
    } 

    while (pq.size() > 1) {
        var min1 = pq.remove(); 
        var min2 = pq.remove(); 

        var newNodeData = min1.data + min2.data; 
        var newNode = new HuffmanNode(newNodeData); 
        newNode.left = min1; 
        newNode.right = min2; 

        pq.insert (newNode); 
    } 

    var huffmanTreeRoot = pq.remove(); 
    var q = new StackQueue.Queue(); 
    var code = {}; 
    huffmanCoding_code (huffmanTreeRoot, q, code); 

    return code; 
} 

function huffmanCoding_code (node, q, code) {
    if (node.hasLeft()) {
        q.add (0); 
        huffmanCoding_code (node.left, q, code); 
        q.remove(); 
    } else {
        code[node.item] = q.data.join ('');
    }

    if (node.hasRight()) {
        q.add(1); 
        huffmanCoding_code (node.right, q, code); 
        q.remove(); 
    } else {
        code[node.item] = q.data.join ('');
    } 
} 

function test_huffmanCoding () {
    var a = [ 'a', 'b', 'c', 'd', 'e', 'f' ]; 
    var f =  [ 12, 2, 7, 13, 14, 85 ]; 
    $.log(huffmanCoding (a, f));
} 
test_huffmanCoding (); 

/* Interval Scheduling */ 

class IntervalSchedulingActivity {
    constructor (name, start, end) {
        this.name = name; 
        this.start = start; 
        this.end = end; 
    } 
}

function intervalScheduling (activities) {
    $.log ('intervalScheduling: '); 

    activities.sort((a, b) => a.end - b.end); 

    var schedule = []; 
    for (a of activities) {
        if (intervalScheduling_isCompatible (schedule, a)) {
            schedule.push (a); 
        }
    } 

    return schedule; 
} 

function intervalScheduling_isCompatible (schedule, activity) {
    return schedule.length === 0 || schedule[schedule.length-1].end <= activity.start; 
}

function test_intervalScheduling () {
    var activities = []; 
    activities.push (new IntervalSchedulingActivity('a', 0, 6)); 
    activities.push (new IntervalSchedulingActivity('b', 1, 4)); 
    activities.push (new IntervalSchedulingActivity('c', 3, 5)); 
    activities.push (new IntervalSchedulingActivity('d', 3, 8)); 
    activities.push (new IntervalSchedulingActivity('e', 4, 7)); 
    activities.push (new IntervalSchedulingActivity('f', 5, 9)); 
    activities.push (new IntervalSchedulingActivity('g', 6, 10)); 
    activities.push (new IntervalSchedulingActivity('h', 8, 11)); 

    var schedule = intervalScheduling (activities); 
    for (s of schedule) {
        $.log(s);
    }
}

test_intervalScheduling (); 