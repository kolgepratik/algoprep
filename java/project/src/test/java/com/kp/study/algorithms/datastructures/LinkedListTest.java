package com.kp.study.algorithms.datastructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListTest {

    @Test
    void testAppend() {
        LinkedList linkedList = new LinkedList();
        assertNull(linkedList.head);

        // Act
        linkedList.append(1);

        assertNotNull(linkedList.head);
        assertEquals(1, linkedList.sizeRecursive());
    }
}