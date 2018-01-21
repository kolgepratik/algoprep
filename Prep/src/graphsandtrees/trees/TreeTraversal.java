package graphsandtrees.trees;

import graphsandtrees.trees.btree.BTree;
import stacksandqueues.GenericQueue;

public class TreeTraversal {

	/**
	 * root > left > right
	 * 
	 * @param node
	 */
	public void printPreorderTraversal(TreeNode node) {
		if (node == null) {
			return;
		}

		// Visit current node.
		System.out.print(" " + node.key + " ");

		// Visit left node.
		printPreorderTraversal(node.left);

		// Visit right node.
		printPreorderTraversal(node.right);
	}

	/**
	 * left > root > right
	 * 
	 * @param node
	 */
	public void printInorderTraversal(TreeNode node) {
		if (node == null) {
			return;
		}

		// Visit left node.
		printInorderTraversal(node.left);

		// Visit current node.
		System.out.print(" " + node.key + " ");

		// Visit right node.
		printInorderTraversal(node.right);
	}

	/**
	 * left > right > root
	 * 
	 * @param node
	 */
	public void printPostorderTraversal(TreeNode node) {
		if (node == null) {
			return;
		}

		// Visit left node.
		printPostorderTraversal(node.left);

		// Visit right node.
		printPostorderTraversal(node.right);

		// Visit current node.
		System.out.print(" " + node.key + " ");

	}

	/**
	 * Level 0 > Level 1 > Level N (N = Height of Tree)
	 * 
	 * @param node
	 */
	public void printLevelOrderTraversal(TreeNode node) {
		GenericQueue<TreeNode> q = new GenericQueue<TreeNode>();

		q.add(node);

		while (!q.isEmpty()) {
			TreeNode current = q.remove();

			System.out.print(" " + current.key + " ");

			if (current.left()) {
				q.add(current.left);
			}

			if (current.right()) {
				q.add(current.right);
			}
		}

	}

	public static void main(String[] args) {
		TreeTraversal tv = new TreeTraversal();

		BTree<Integer> tree = BTree.getSampleIntegerBinaryTree();

		System.out.println("\nPreorder traversal of binary tree is \n");
		tv.printPreorderTraversal(tree.root);

		System.out.println("\nInorder traversal of binary tree is \n");
		tv.printInorderTraversal(tree.root);

		System.out.println("\nPostorder traversal of binary tree is \n");
		tv.printPostorderTraversal(tree.root);

		System.out.println("\nLevelorder traversal of binary tree is \n");
		tv.printLevelOrderTraversal(tree.root);
	}
}
