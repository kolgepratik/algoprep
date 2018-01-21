package graphsandtrees.trees.btree;

import graphsandtrees.trees.TreeNode;

public class BTree<T> {
	public TreeNode<T> root;

	public BTree() {
		super();
	}

	public static BTree<Integer> getSampleIntegerBinaryTree() {
		BTree<Integer> tree = new BTree<Integer>();

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
