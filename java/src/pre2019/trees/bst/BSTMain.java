package pre2019.trees.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import pre2019.trees.TreeNode;
import pre2019.trees.TreeTraversal;

public class BSTMain {

	public static void main(String[] args) {
		BST<Integer> bst = BST.getSampleIntegerBST();
		TreeTraversal tt = new TreeTraversal();
		TreeNode<Integer> node;

		// isBalanced ()
		System.out.println("\nisBalanced: " + bst.isBalanced(bst.root));

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
		bst.insert(1);
		// findMin ()
		node = bst.findMin(bst.root);
		System.out.println("\nMinimum Value after insert: "
				+ ((node != null) ? node.key : " Not found"));

		// insert ()
		bst.insert(42);
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
		node = bst.findKthSmallest(bst.root, 5, new LinkedList<Integer>());
		System.out.println("\nInorder Traversal: ");
		tt.printInorderTraversal(bst.root);
		System.out.println("\nKth Smallest element: "
				+ ((node != null) ? node.key : " Not found"));

		// rotateRight ()
		System.out.println("\nLevelorder traversal before rotation: ");
		tt.printLevelOrderTraversal(bst.root);
		bst.rotateLeft(bst.find(bst.root, 14));
		System.out.println("\nLevelorder traversal after rotation: ");
		tt.printLevelOrderTraversal(bst.root);

		// isBalanced ()
		System.out.println("\nisBalanced: " + bst.isBalanced(bst.root));

		BST<Integer> newbst = new BST<>();
		newbst.insert(50);
		newbst.insert(10);
		newbst.insert(30);
		newbst.insert(5);
		newbst.insert(2);
		newbst.insert(1);
		newbst.insert(70);
		newbst.insert(80);
		newbst.insert(90);
		newbst.insert(100);
		System.out.println("\nnewbst Level order traversal: ");
		tt.printLevelOrderTraversal(newbst.root);

		// balanceFactor ()
		System.out.println("\nbalanceFactor: "
				+ newbst.balanceFactor(newbst.root));

	}
}
