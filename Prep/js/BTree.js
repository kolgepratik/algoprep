var StackQueue = require('./StackQueue.js');

class BTreeNode {
    constructor (data) {
        this.data = data; 
        this.left = undefined; 
        this.right = undefined; 
    } 

    hasLeft () {
        return this.left ? true : false; 
    } 

    hasRight () {
        return this.right ? true : false; 
    } 

    print () {
        console.log('BTreeNode: %s . Left: %s . Right: %s', this.data, this.left ? this.left.data : this.left, this.right ? this.right.data : this.right); 
    } 
} 

class BTree {
    constructor (root) {
        this.root = root; 
    } 

    inorder () {
        if (!this.root) {
            console.log('inorder: BTree is empty');
        } else {
            console.log('inorder: traversal');
            this.inorder_helper(this.root); 
            console.log('inorder: end'); 
        } 
    } 

    inorder_helper (root) {
        if (!root) {
            return; 
        } else {
            this.inorder_helper(root.left);
            console.log(root.data); 
            this.inorder_helper(root.right); 
        }
    } 

    preorder () {
        if (!this.root) {
            console.log('preorder: BTree is empty');
        } else {
            console.log('preorder: traversal');
            this.preorder_helper(this.root); 
            console.log('preorder: end'); 
        } 
    } 

    preorder_helper (root) {
        if (!root) {
            return; 
        } else {
            console.log(root.data); 
            this.preorder_helper(root.left);
            this.preorder_helper(root.right); 
        }
    } 

    postorder () {
        if (!this.root) {
            console.log('postorder: BTree is empty');
        } else {
            console.log('postorder: traversal');
            this.postorder_helper(this.root); 
            console.log('postorder: end'); 
        } 
    } 

    postorder_helper (root) {
        if (!root) {
            return; 
        } else {
            this.postorder_helper(root.left);
            this.postorder_helper(root.right); 
            console.log(root.data); 
        }
    } 

    levelorder () {
        if (!this.root) {
            console.log('levelorder: BTree is empty');
        } else { 
            console.log('levelorder: traversal'); 
            var q = new StackQueue.Queue(); 
            q.add(this.root); 
            while (!q.isEmpty()) { 
                var current = q.remove(); 
                console.log(current.data);
                if (current.hasLeft()) {
                    q.add(current.left); 
                } 
                if (current.hasRight()) {
                    q.add(current.right); 
                } 
            }
            console.log('levelorder: end'); 
        } 
    } 
} 

var btnode = new BTreeNode(1);
btnode.left = new BTreeNode(2);
btnode.right = new BTreeNode(3);
btnode.left.left = new BTreeNode(4); 
btnode.left.right = new BTreeNode(5); 
btnode.right.left = new BTreeNode(6); 
btnode.right.right = new BTreeNode(7); 

var bt = new BTree(btnode); 
bt.preorder(); 
bt.inorder();
bt.postorder(); 
bt.levelorder(); 
