package graphsandtrees.trees;

public class TreeNode<T> implements Comparable<T> {
	public T key;

	public TreeNode<T> left;
	public TreeNode<T> right;

	public TreeNode(T key) {
		super();
		this.key = key;
	}

	public boolean left() {
		return (this.left != null);
	}

	public boolean right() {
		return (this.right != null);
	}

	@Override
	public String toString() {
		return "TreeNode [key=" + key + "]";
	}

	public boolean greaterThan(T obj) {
		if (compareTo(obj) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean lessThan(T obj) {
		if (compareTo(obj) < 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equalTo(T obj) {
		if (compareTo(obj) == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(T obj) {
		if (obj instanceof Integer) {
			return ((Integer) key).compareTo((Integer) obj);
		} else {
			return 0;
		}
	}
}
