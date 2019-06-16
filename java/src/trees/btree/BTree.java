package trees.btree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import trees.TreeNode;

public class BTree<T> {
	public TreeNode<T> root;

	public BTree() {
		super();
	}

	public static BTree<Integer> getSampleIntegerBinaryTree() {
		BTree<Integer> tree = new BTree<Integer>();

		tree.root = new TreeNode(1);

		tree.root.left = new TreeNode(2);
		tree.root.left.left = new TreeNode(4);
		tree.root.left.right = new TreeNode(5);

		tree.root.right = new TreeNode(3);
		tree.root.right.left = new TreeNode(6);
		tree.root.right.right = new TreeNode(7);

		return tree;
	}

	/**
	 * Count the number of leaves in the tree,
	 * 
	 * @param btree
	 * @return
	 */
	int countLeaves(BTree<Integer> btree) {
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
	void printAllRootToLeavesPaths(BTree<Integer> btree) {
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

	/**
	 * Build a Binary Tree using Inorder and Preorder Traversal.
	 * 
	 * @param root
	 * @param preorderTraversal
	 * @param inorderTraversal
	 * @return
	 */
	TreeNode<Integer> buildBinaryTreeFromInAndPreorderTraversal(TreeNode<Integer> root, List<Integer> preorderTraversal,
			List<Integer> inorderTraversal) {

		if (inorderTraversal == null || inorderTraversal.isEmpty() || preorderTraversal == null
				|| preorderTraversal.isEmpty()) {
			return null;
		} else {
			Integer key = preorderTraversal.get(0);

			// Create the Node.
			root = new TreeNode<Integer>(key);

			// Node created. Move to the next node.
			preorderTraversal.remove(0);

			// Find left and right sublists.
			List<Integer> leftList = inorderTraversal.subList(0, inorderTraversal.indexOf(key));
			List<Integer> rightList = inorderTraversal.subList(inorderTraversal.indexOf(key) + 1,
					inorderTraversal.size());

			// Create left subtree.
			root.left = buildBinaryTreeFromInAndPreorderTraversal(root.left, preorderTraversal, leftList);

			// Create right subtree.
			root.right = buildBinaryTreeFromInAndPreorderTraversal(root.right, preorderTraversal, rightList);

			return root;
		}
	}

	/**
	 * Print vertical sum for the btree. Use horizontal distance (hd) from root
	 * to find vertical-sum. If going left: [hd = hd - 1]. If going right: [ hd
	 * = hd + 1]. Use HashMap to store the hd and its sum value.
	 * 
	 * @param root
	 */
	void verticalSum(TreeNode<Integer> root) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

		verticalSum_helper(hm, root, 0);

		int columnNumber = 1;
		for (Map.Entry<Integer, Integer> vd : hm.entrySet()) {
			System.out.println("Column " + columnNumber++ + ": " + vd.getValue() + " ");
		}
	}

	void verticalSum_helper(HashMap<Integer, Integer> hm, TreeNode<Integer> node, int hd) {
		if (node == null) {
			return;
		} else {
			hm.put(hd, (hm.get(hd) != null) ? hm.get(hd) + node.key : node.key);

			if (node.left()) {
				verticalSum_helper(hm, node.left, hd - 1);
			}

			if (node.right()) {
				verticalSum_helper(hm, node.right, hd + 1);
			}
		}
	}
}
