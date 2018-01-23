package graphsandtrees.trees.bst;

import graphsandtrees.trees.TreeNode;
import graphsandtrees.trees.TreeTraversal;

import java.util.ArrayList;
import java.util.Arrays;

public class BSTMain {

	public static void main(String[] args) {
		BST<Integer> bst = BST.getSampleIntegerBST();
		TreeTraversal tt = new TreeTraversal();
		TreeNode<Integer> node;

		// isInorderBST ()
		System.out.println("isInorderBST: "
				+ bst.isInorderBST(bst.root, Integer.MIN_VALUE));

		// isBST ()
		System.out.println("isBST: "
				+ bst.isBST(bst.root, Integer.MIN_VALUE, Integer.MAX_VALUE));

		// find ()
		node = bst.find(bst.root, 2);
		System.out.println("\nFinding value in BST: "
				+ ((node != null) ? node.key : " Not found"));

		// findMin ()
		node = bst.findMin(bst.root);
		System.out.println("\nMinimum Value: "
				+ ((node != null) ? node.key : " Not found"));

		// findMax ()
		node = bst.findMax(bst.root);
		System.out.println("\nMaximum Value: "
				+ ((node != null) ? node.key : " Not found"));

		// insert ()
		bst.insert(bst.root, 1);
		// findMin ()
		node = bst.findMin(bst.root);
		System.out.println("\nMinimum Value after insert: "
				+ ((node != null) ? node.key : " Not found"));

		// insert ()
		bst.insert(bst.root, 42);
		// findMin ()
		node = bst.findMax(bst.root);
		System.out.println("\nMaximum Value after insert: "
				+ ((node != null) ? node.key : " Not found"));

		System.out.println("\nInorder traversal: ");
		tt.printInorderTraversal(bst.root);

		// delete ()
		bst.delete(bst.root, 42);
		System.out.println("\nInorder traversal after deletion: ");
		tt.printInorderTraversal(bst.root);

		// findLCA ()
		node = bst.findLCA(bst.root, 16, 22);
		System.out.println("\nLCA is: "
				+ ((node != null) ? node.key : " Not found"));

		// sortedArrayToBST ()
		node = bst.sortedArrayToBST(new ArrayList<Integer>(Arrays
				.asList(new Integer[] { 3, 7, 10, 14, 21 })));
		System.out.println("\nInorder traversal after tree construction: ");
		tt.printInorderTraversal(node);

		// findKthSmallest ()
		node = bst.findKthSmallest(bst.root, 3, 1);
		System.out.println("\nKth Smallest element: "
				+ ((node != null) ? node.key : " Not found"));
	}
}
