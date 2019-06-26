package com.kp.study.algorithms.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    LinkedListNode getLastNode() {
        return this.getLastHelper(this.head);
    }

    LinkedListNode getNodeAtPosition(int index) {
        LinkedListNode nodePointer = this.head;

        for (int i = 1; nodePointer != null && i < index; i++)
            nodePointer = nodePointer.next;

        return nodePointer;
    }

    private LinkedListNode getLastHelper(LinkedListNode nodePointer) {
        if (nodePointer == null || nodePointer.next == null)
            return nodePointer;

        return this.getLastHelper(nodePointer.next);
    }

    void swapNodes(int firstNodeData, int secondNodeData) {
        if (this.head != null && (firstNodeData != secondNodeData)) {
            LinkedListNode first = this.head;
            LinkedListNode firstPrev = null;

            while (first != null) {
                if (first.data == firstNodeData) {
                    break;
                }

                firstPrev = first;
                first = first.next;
            }

            LinkedListNode second = this.head;
            LinkedListNode secondPrev = null;
            while (second != null) {
                if (second.data == secondNodeData) {
                    break;
                }

                secondPrev = second;
                second = second.next;
            }

            if (first != null && second != null) {
                if (firstPrev == null) {
                    second.next = first.next;
                    this.head = second;

                    first.next = null;
                    secondPrev.next = first;
                }
                else if (secondPrev == null) {
                    first.next = second.next;
                    this.head = first;

                    second.next = null;
                    firstPrev.next = second;
                }
                else {
                    LinkedListNode firstNext = first.next;
                    LinkedListNode secondNext = second.next;
                    firstPrev.next = second;
                    secondPrev.next = first;

                    second.next = firstNext;
                    first.next = secondNext;
                }

                System.out.println("List After swapping: " + this);
            }
        }
    }

    public void swapPairwise() {
        if (this.head != null) {
            LinkedListNode first = this.head;
            LinkedListNode second = first.next;

            if (second != null) {
                this.head = second;
                first.next = second.next;
                second.next = first;

                LinkedListNode prev = first;
                LinkedListNode current = first.next;
                while(current != null && current.next != null) {
                    LinkedListNode next = current.next;

                    prev.next = next;
                    current.next = next.next;
                    next.next = current;

                    prev = current;
                    current = current.next;
                }

                System.out.println("List After swapping: " + this);
            }
        }
    }

    @Override
    public String toString() {
        final String allNodes =
                this.getAll().stream().map(LinkedListNode::toString).collect(Collectors.joining("\n"));

        return "\nLinkedList {" + "head=" + head + "} " + "\nAll Nodes: \n" + allNodes;
    }
}