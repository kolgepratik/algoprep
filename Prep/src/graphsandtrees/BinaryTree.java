package graphsandtrees;

public class BinaryTree<T> {
	TreeNode<T> root;

	public BinaryTree() {
		super();
	}

	public static BinaryTree<Integer> getSampleIntegerBinaryTree() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();

		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(3);
		tree.root.right.left = new TreeNode(7);
		tree.root.right.right = new TreeNode(8);
		tree.root.left.left = new TreeNode(4);
		tree.root.left.right = new TreeNode(5);
		tree.root.left.right.right = new TreeNode(6);

		return tree;
	}
}
