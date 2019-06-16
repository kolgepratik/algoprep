/**
 * 
 */
package heaps;

/**
 * @author T2590PK
 *
 */
public class BinHeap {

	public enum HEAP_TYPE {
		MIN_HEAP, MAX_HEAP
	};

	private static final Integer CAPACITY_FACTOR = 2;

	public Integer[] data;
	public HEAP_TYPE type;
	public Integer capacity;
	public Integer size;

	public BinHeap(HEAP_TYPE type, Integer capacity) {
		super();
		this.type = type;
		this.capacity = capacity;
		this.data = new Integer[capacity];
		this.size = 0;
	}

	public Integer get(int index) {
		return (index == -1) ? ((this.type == HEAP_TYPE.MAX_HEAP) ? Integer.MIN_VALUE
				: Integer.MAX_VALUE)
				: this.data[index];
	}

	public void set(int index, Integer data) {
		if (index != -1 && index >= 0 && index < size)
			this.data[index] = data;
	}

	/**
	 * Get parent's index.
	 * 
	 * @param index
	 * @return
	 */
	public int parent(int index) {
		int parentidx = (index - 1) / 2;
		return (parentidx < 0 || parentidx > (size - 1)) ? -1 : parentidx;
	}

	/**
	 * Get left child of given index.
	 * 
	 * @param index
	 * @return
	 */
	public int left(int index) {
		int leftidx = (2 * index) + 1;
		return (leftidx < 0 || leftidx > (size - 1)) ? -1 : leftidx;
	}

	/**
	 * Get right child of given index.
	 * 
	 * @param index
	 * @return
	 */
	public int right(int index) {
		int rightidx = (2 * index) + 2;
		return (rightidx < 0 || rightidx > (size - 1)) ? -1 : rightidx;
	}

	public boolean isempty() {
		return (this.size == 0 || this.capacity == 0);
	}

	/**
	 * Increase capacity of the heap by given factor.
	 */
	private void increaseCapacity() {
		this.capacity *= BinHeap.CAPACITY_FACTOR;

		Integer[] newdata = new Integer[this.capacity];
		for (int i = 0; i < this.size; i++) {
			newdata[i] = this.data[i];
		}

		this.data = newdata;
	}

	public void insert(Integer item) {
		if (this.capacity == this.size)
			increaseCapacity();

		int itemindex = this.size;
		this.data[itemindex] = item;
		this.size++;

		heapifyUp(itemindex);
	}

	public Integer remove() {
		if (!this.isempty()) {
			int lastidx = this.size - 1;
			int firstitem = this.get(0);

			this.set(0, this.get(lastidx));
			this.size--;

			heapifyDown(0);

			return firstitem;
		} else {
			return null;
		}
	}

	public void heapifyUp(int index) {
		int parentidx = this.parent(index);

		int swapidx = this.isvalidparent(parentidx);
		if (swapidx != -1) {
			Integer temp = this.get(swapidx);
			this.set(swapidx, this.get(parentidx));
			this.set(parentidx, temp);

			heapifyUp(parentidx);
		}
	}

	public void heapifyDown(int index) {
		int swapidx = this.isvalidparent(index);

		if (swapidx != -1) {
			Integer temp = this.get(swapidx);
			this.set(swapidx, this.get(index));
			this.set(index, temp);

			heapifyDown(swapidx);
		}
	}

	private int isvalidparent(int parent) {
		int idx = parent;
		int left = this.left(idx);
		int right = this.right(idx);

		if (this.type == HEAP_TYPE.MAX_HEAP) {
			if (left != -1 && this.get(left) > this.get(parent)) {
				idx = left;
			}

			if (right != -1 && this.get(right) > this.get(idx)) {
				idx = right;
			}

		} else if (this.type == HEAP_TYPE.MIN_HEAP) {
			if (left != -1 && this.get(left) < this.get(parent)) {
				idx = left;
			}

			if (right != -1 && this.get(right) < this.get(idx)) {
				idx = right;
			}
		}

		return (idx == parent) ? -1 : idx;
	}

	public void print() {
		System.out.println("\nHeap: ");
		for (int i = 0; i < this.size; i++) {
			System.out.print(" " + this.data[i]);
		}
		System.out.println("\n");
	}

	public static void main(String[] s) {
		BinHeap binheap = new BinHeap(HEAP_TYPE.MAX_HEAP, 5);

		binheap.insert(10);
		binheap.insert(5);
		binheap.insert(20);
		binheap.insert(1);
		binheap.insert(26);

		binheap.print();

		System.out.println("Removed: " + binheap.remove());

		binheap.print();

	}

}
