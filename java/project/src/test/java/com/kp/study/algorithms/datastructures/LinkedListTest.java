package com.kp.study.algorithms.datastructures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

class LinkedListTest {

    @Test
    void append() {
        LinkedList linkedList = new LinkedList();
        assertNull(linkedList.head);

        // Act
        linkedList.append(1);

        assertNotNull(linkedList.head);
        assertEquals(1, linkedList.sizeRecursive());
    }

    @Test
    void getNodeAtPosition() {
        LinkedList linkedList = new LinkedList();

        appendItems(linkedList, 1, 5, integer -> integer + 1);
        Assumptions.assumeTrue(5 == linkedList.sizeRecursive());

        // Act
        LinkedListNode first = linkedList.getNodeAtPosition(1);
        assertNotNull(first);
        assertEquals(1, first.data);

        LinkedListNode second = linkedList.getNodeAtPosition(2);
        assertNotNull(second);
        assertEquals(2, second.data);

        LinkedListNode third = linkedList.getNodeAtPosition(3);
        assertNotNull(third);
        assertEquals(3, third.data);

        LinkedListNode fourth = linkedList.getNodeAtPosition(4);
        assertNotNull(fourth);
        assertEquals(4, fourth.data);

        LinkedListNode fifth = linkedList.getNodeAtPosition(5);
        assertNotNull(fifth);
        assertEquals(5, fifth.data);

        LinkedListNode tenth = linkedList.getNodeAtPosition(10);
        assertNull(tenth);
    }

    @Test
    void swapNodes() {
        LinkedList linkedList = new LinkedList();

        appendItems(linkedList, 1, 5, integer -> integer + 1);
        Assumptions.assumeTrue(5 == linkedList.sizeRecursive());

        Assumptions.assumeTrue(2 == linkedList.getNodeAtPosition(2).data);
        Assumptions.assumeTrue(4 == linkedList.getNodeAtPosition(4).data);

        // Act
        linkedList.swapNodes(2, 4);

        assertEquals(4, linkedList.getNodeAtPosition(2).data);
        assertEquals(2, linkedList.getNodeAtPosition(4).data);

        // Act
        linkedList.swapNodes(1, 5);

        assertEquals(5, linkedList.getNodeAtPosition(1).data);
        assertEquals(1, linkedList.getNodeAtPosition(5).data);

        // Act
        linkedList.swapNodes(2, 4);

        assertEquals(2, linkedList.getNodeAtPosition(2).data);
        assertEquals(4, linkedList.getNodeAtPosition(4).data);

        // Act
        linkedList.swapNodes(1, 5);

        assertEquals(1, linkedList.getNodeAtPosition(1).data);
        assertEquals(5, linkedList.getNodeAtPosition(5).data);
    }

    @Test
    void swapPairwise() {
        LinkedList evenLinkedList = new LinkedList();

        appendItems(evenLinkedList, 1, 6, integer -> integer + 1);
        Assumptions.assumeTrue(6 == evenLinkedList.sizeRecursive());

        Assumptions.assumeTrue(1 == evenLinkedList.getNodeAtPosition(1).data);
        Assumptions.assumeTrue(2 == evenLinkedList.getNodeAtPosition(2).data);
        Assumptions.assumeTrue(3 == evenLinkedList.getNodeAtPosition(3).data);
        Assumptions.assumeTrue(4 == evenLinkedList.getNodeAtPosition(4).data);
        Assumptions.assumeTrue(5 == evenLinkedList.getNodeAtPosition(5).data);
        Assumptions.assumeTrue(6 == evenLinkedList.getNodeAtPosition(6).data);

        // Act
        evenLinkedList.swapPairwise();

        assertEquals(2, evenLinkedList.getNodeAtPosition(1).data);
        assertEquals(1, evenLinkedList.getNodeAtPosition(2).data);
        assertEquals(4, evenLinkedList.getNodeAtPosition(3).data);
        assertEquals(3, evenLinkedList.getNodeAtPosition(4).data);
        assertEquals(6, evenLinkedList.getNodeAtPosition(5).data);
        assertEquals(5, evenLinkedList.getNodeAtPosition(6).data);

        LinkedList oddLinkedList = new LinkedList();

        appendItems(oddLinkedList, 1, 7, integer -> integer + 1);
        Assumptions.assumeTrue(7 == oddLinkedList.sizeRecursive());

        Assumptions.assumeTrue(1 == oddLinkedList.getNodeAtPosition(1).data);
        Assumptions.assumeTrue(2 == oddLinkedList.getNodeAtPosition(2).data);
        Assumptions.assumeTrue(3 == oddLinkedList.getNodeAtPosition(3).data);
        Assumptions.assumeTrue(4 == oddLinkedList.getNodeAtPosition(4).data);
        Assumptions.assumeTrue(5 == oddLinkedList.getNodeAtPosition(5).data);
        Assumptions.assumeTrue(6 == oddLinkedList.getNodeAtPosition(6).data);
        Assumptions.assumeTrue(7 == oddLinkedList.getNodeAtPosition(7).data);

        // Act
        oddLinkedList.swapPairwise();

        assertEquals(2, oddLinkedList.getNodeAtPosition(1).data);
        assertEquals(1, oddLinkedList.getNodeAtPosition(2).data);
        assertEquals(4, oddLinkedList.getNodeAtPosition(3).data);
        assertEquals(3, oddLinkedList.getNodeAtPosition(4).data);
        assertEquals(6, oddLinkedList.getNodeAtPosition(5).data);
        assertEquals(5, oddLinkedList.getNodeAtPosition(6).data);
        assertEquals(7, oddLinkedList.getNodeAtPosition(7).data);

    }

    private void appendItems(LinkedList linkedList, int seed, int limit, Function<Integer, Integer> mapper) {
        Stream.iterate(seed, mapper::apply).limit(limit).forEach(linkedList::append);
    }
}