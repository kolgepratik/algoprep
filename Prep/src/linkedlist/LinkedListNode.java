package linkedlist;

public class LinkedListNode<T> {
	T data;
	LinkedListNode<T> next;
	LinkedListNode<T> prev;

	public LinkedListNode(T data) {
		super();
		this.data = data;
	}

	public LinkedListNode(T data, LinkedListNode<T> next) {
		super();
		this.data = data;
		this.next = next;
	}

	public LinkedListNode(T data, LinkedListNode<T> next, LinkedListNode<T> prev) {
		super();
		this.data = data;
		this.next = next;
		this.prev = prev;
	}

	@Override
	public String toString() {
		return "" + data;
	}

}
