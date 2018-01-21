package graphsandtrees.trees;

public class TreeNode<T> {
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
}
