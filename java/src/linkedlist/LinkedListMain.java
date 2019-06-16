package linkedlist;

public class LinkedListMain {
	public static void main(String[] a) {
		SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addLast(1);
		sll.addLast(2);
		sll.addLast(3);
		sll.addLast(4);
		sll.addLast(5);

		sll.swapk(2);

		sll.print();
	}
}
