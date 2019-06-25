package com.kp.study.algorithms.datastructures;

import java.util.ArrayList;
import java.util.List;

class LinkedListNode {
    int data;

    LinkedListNode next;

    public LinkedListNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LinkedListNode {" + "data=" + data + ", next=" + ((next == null) ? next : next.data) + '}';
    }
}

public class LinkedList {
    LinkedListNode head;

    List<LinkedListNode> getAll() {
        List<LinkedListNode> allNodes = new ArrayList<>();

        LinkedListNode nodePointer = this.head;
        while (nodePointer != null) {
            allNodes.add(nodePointer);
            nodePointer = nodePointer.next;
        }

        return allNodes;
    }

    void append(int data) {
        LinkedListNode newNode = new LinkedListNode(data);

        if (this.head == null) {
            this.head = newNode;
            return;
        }

        LinkedListNode nodePointer = this.head;
        while (nodePointer.next != null)
            nodePointer = nodePointer.next;

        nodePointer.next = newNode;
    }

    int sizeRecursive() {
        return this.sizeRecursiveHelper(this.head);
    }

    private int sizeRecursiveHelper(LinkedListNode currentNode) {
        if (currentNode == null) {
            return 0;
        }

        return 1 + this.sizeRecursiveHelper(currentNode.next);
    }

    LinkedListNode getLast() {
        return this.getLastHelper(this.head);
    }

    private LinkedListNode getLastHelper(LinkedListNode nodePointer) {
        if (nodePointer == null || nodePointer.next == null)
            return nodePointer;

        return this.getLastHelper(nodePointer.next);
    }
}