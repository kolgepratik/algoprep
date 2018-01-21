/**
 * 
 */
package graphsandtrees.trees.bst;

import graphsandtrees.trees.TreeNode;

/**
 * @author kolge
 *
 */
public class BST<T> {
	TreeNode<T> root;

	public BST() {
		super();
	}

	public static BST<Integer> getSampleIntegerBinaryTree() {
		BST<Integer> tree = new BST<Integer>();

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
