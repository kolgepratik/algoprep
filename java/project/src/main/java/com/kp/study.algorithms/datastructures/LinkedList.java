package com.kp.study.algorithms.datastructures;

import java.util.ArrayList;
import java.util.List;

class LLNode {
    int data;

    LLNode next;

    public LLNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LLNode {" + "data=" + data + ", next=" + ((next == null) ? next : next.data) + '}';
    }
}

public class LinkedList {
    LLNode head;

    List<LLNode> getAll() {
        List<LLNode> allNodes = new ArrayList<>();

        LLNode nodePointer = this.head;
        while (nodePointer != null) {
            allNodes.add(nodePointer);
            nodePointer = nodePointer.next;
        }

        return allNodes;
    }

    void append(int data) {
        LLNode newNode = new LLNode(data);

        if (this.head == null) {
            this.head = newNode;
            return;
        }

        LLNode nodePointer = this.head;
        while (nodePointer.next != null)
            nodePointer = nodePointer.next;

        nodePointer.next = newNode;
    }

    int lengthRecursive() {
        return this.lengthRecursiveHelper(this.head);
    }

    private int lengthRecursiveHelper(LLNode currentNode) {
        if (currentNode == null) {
            return 0;
        }

        return 1 + this.lengthRecursiveHelper(currentNode.next);
    }

    LLNode getLast() {
        return this.getLastHelper(this.head);
    }

    private LLNode getLastHelper(LLNode nodePointer) {
        if (nodePointer == null || nodePointer.next == null)
            return nodePointer;

        return this.getLastHelper(nodePointer.next);
    }
}