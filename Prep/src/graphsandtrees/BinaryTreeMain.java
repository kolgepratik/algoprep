/**
 * 
 */
package graphsandtrees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kolge
 *
 */
public class BinaryTreeMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTreeMain binaryTreeMain = new BinaryTreeMain();
		TreeTraversal treeTraversal = new TreeTraversal();

		BinaryTree<Integer> tree = BinaryTree.getSampleIntegerBinaryTree();

		// System.out.println("Number of leaf nodes in Tree : " +
		// binaryTreeMain.countLeaves(tree));

		// System.out.println("All Paths from root to leaves : ");
		// binaryTreeMain.printAllRootToLeavesPaths(tree);

		System.out.println("\nInitial Tree : ");
		treeTraversal.printLevelOrderTraversal(tree.root);

		System.out.println("\nMirrored Tree : ");
		TreeNode<Integer> mirrorTree = binaryTreeMain.mirror(tree.root);
		treeTraversal.printLevelOrderTraversal(mirrorTree);

		System.out.println("\nLCA is : "
				+ binaryTreeMain.findLCA(tree.root, new TreeNode<Integer>(4), new TreeNode<Integer>(8)).key);

		List<Integer> inorder = Arrays.asList(new Integer[] { 4, 2, 5, 1, 6, 3, 7 });
		List<Integer> preorder = Arrays.asList(new Integer[] { 1, 2, 4, 5, 3, 6, 7 });

		System.out.println("\nConstructed Tree: ");
		TreeNode<Integer> root = binaryTreeMain.buildBinaryTreeFromInAndPreorderTraversal(null, preorder, inorder);
		treeTraversal.printLevelOrderTraversal(root);
	}

	/**
	 * Count the number of leaves in the tree,
	 * 
	 * @param btree
	 * @return
	 */
	int countLeaves(BinaryTree<Integer> btree) {
		int leafCount = 0;

		LinkedList<TreeNode> q = new LinkedList<TreeNode>();

		q.add(btree.root);

		while (!q.isEmpty()) {
			TreeNode current = q.poll();

			if (!current.left() && !current.right()) {
				leafCount++;
			}

			if (current.left()) {
				q.add(current.left);
			}

			if (current.right()) {
				q.add(current.right);
			}
		}

		return leafCount;
	}

	/**
	 * Print all paths from Root to Leaves.
	 * 
	 * @param btree
	 */
	void printAllRootToLeavesPaths(BinaryTree<Integer> btree) {
		LinkedList<TreeNode<Integer>> q = new LinkedList<TreeNode<Integer>>();

		TreeNode<Integer> root = btree.root;
		q.add(root);

		printAllRootToLeavesPaths_helper(root, q);
	}

	void printAllRootToLeavesPaths_helper(TreeNode<Integer> current, LinkedList<TreeNode<Integer>> queue) {
		if (!current.left() && !current.right()) {
			System.out.println("");
			for (int i = 0; i < queue.size(); i++) {
				System.out.print(" > " + queue.get(i).key + " ");
			}
		}

		if (current.left()) {
			queue.add(current.left);
			printAllRootToLeavesPaths_helper(current.left, queue);
			queue.pollLast();
		}

		if (current.right()) {
			queue.add(current.right);
			printAllRootToLeavesPaths_helper(current.right, queue);
			queue.pollLast();
		}
	}

	/**
	 * Mirror the given Tree.
	 * 
	 * @param root
	 * @return
	 */
	TreeNode<Integer> mirror(TreeNode<Integer> current) {
		if (current.left()) {
			mirror(current.left);
		}

		if (current.right()) {
			mirror(current.right);
		}

		TreeNode<Integer> temp = current.left;
		current.left = current.right;
		current.right = temp;

		return current;
	}

	/**
	 * Find the Least Common Ancestor for the given 2 nodes.
	 * 
	 * @param current
	 * @param n1
	 * @param n2
	 * @return
	 */
	TreeNode<Integer> findLCA(TreeNode<Integer> current, TreeNode<Integer> n1, TreeNode<Integer> n2) {
		if (current == null) {
			return null;
		}

		// If the current node contains either of the keys, return the current
		// node.
		if (current.key == n1.key || current.key == n2.key) {
			return current;
		}

		TreeNode<Integer> leftNode = findLCA(current.left, n1, n2);
		TreeNode<Integer> rightNode = findLCA(current.right, n1, n2);

		if (leftNode != null && rightNode != null) {
			return current;
		} else {
			return (leftNode != null) ? leftNode : rightNode;
		}
	}

	TreeNode<Integer> buildBinaryTreeFromInAndPreorderTraversal(TreeNode<Integer> root, List<Integer> preorderTraversal,
			List<Integer> inorderTraversal) {

		if (inorderTraversal.isEmpty() || preorderTraversal.isEmpty()) {
			System.out.print("Empty\n");
			return null;
		}

		Integer key = preorderTraversal.get(0);

		System.out.println("Made : " + key);
		System.out.println("Pre : " + preorderTraversal);
		System.out.println("In : " + inorderTraversal);

		if (root == null) {
			root = new TreeNode<Integer>(key);
			preorderTraversal = preorderTraversal.subList(1, preorderTraversal.size());
		}

		inorderTraversal = inorderTraversal.subList(0, inorderTraversal.indexOf(key));

		System.out.println("\nGoing left");
		root.left = buildBinaryTreeFromInAndPreorderTraversal(root.left, preorderTraversal, inorderTraversal);

		inorderTraversal = inorderTraversal.subList(inorderTraversal.indexOf(key) + 1, inorderTraversal.size());
		System.out.println("\nGoing right");
		root.right = buildBinaryTreeFromInAndPreorderTraversal(root.right, preorderTraversal, inorderTraversal);

		return root;
	}
}
