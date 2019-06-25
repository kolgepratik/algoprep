package com.kp.study.algorithms.old.linkedlist;

import com.kp.study.algorithms.old.linkedlist.LinkedListNode;

public class SinglyLinkedList<T> {
	LinkedListNode<T> head;

	public LinkedListNode<T> addLast(T data) {
		LinkedListNode<T> node;
		if (head == null) {
			node = new LinkedListNode<T>(data);
			head = node;
			return node;
		} else {
			node = head;
			while (node.next != null) {
				node = node.next;
			}
			node.next = new LinkedListNode<T>(data);
			return node.next;
		}
	}

	public void print() {
		LinkedListNode<T> node = head;

		System.out.println("List: ");
		while (node != null) {
			System.out.println(" --> " + node.data);
			node = node.next;
		}
		System.out.println(" >>> End ");
	}

	public void swapk(int k) {
		if (k < 1) {
			System.err.println("k must be greater than zero");
		} else {
			LinkedListNode<T> p, q, prev, x, xp, y, yp;
			int counter = 1;

			if (head != null) {
				prev = null;
				p = head;
				while (counter++ < k) {
					if (p.next == null) {
						break;
					} else {
						prev = p;
						p = p.next;
					}
				}

				if (p.next == null && counter <= k) {
					System.err.println("List size is smaller than " + k);
				} else {
					x = p;
					xp = prev;

					prev = null;
					q = head;
					while (p.next != null) {
						prev = q;
						p = p.next;
						q = q.next;

						counter++;
					}

					y = q;
					yp = prev;

					if (x == null || y == null) {
						System.err.println("Error. x or y is null.");
					} else {
						System.out.println("x: " + x + ", xp: " + xp + ", y: "
								+ y + ", yp: " + yp + ", counter: " + counter);

						if (xp != null) {
							xp.next = y;
						}

						if (yp != null) {
							yp.next = x;
						}

						p = x.next;
						x.next = y.next;
						y.next = p;

						if (k == 1) {
							head = y;
						} else if (k == (counter - 1)) {
							head = x;
						}
					}
				}
			} else {
				System.err.println("List is empty.");
			}
		}
	}
}
