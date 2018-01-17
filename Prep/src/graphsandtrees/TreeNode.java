package graphsandtrees;

public class TreeNode<T> {
	T key;

	TreeNode<T> left;
	TreeNode<T> right;

	public TreeNode(T key) {
		super();
		this.key = key;
	}

	boolean left() {
		return (this.left != null);
	}

	boolean right() {
		return (this.right != null);
	}
}
