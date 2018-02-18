/**
 * 
 */
package trees.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trees.TreeNode;
import trees.TreeTraversal;

/**
 * @author kolge
 *
 */
public class BTreeMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeTraversal treeTraversal = new TreeTraversal();
		BTree<Integer> btree = BTree.getSampleIntegerBinaryTree();

		// countLeaves ()
		System.out.println("Number of leaf nodes in Tree : " + btree.countLeaves(btree));

		// printAllRootToLeavesPaths ()
		System.out.println("All Paths from root to leaves : ");
		btree.printAllRootToLeavesPaths(btree);

		// printLevelOrderTraversal ()
		System.out.println("\nInitial Tree : ");
		treeTraversal.printLevelOrderTraversal(btree.root);

		// zigzagTraversal ()
		System.out.println("\nZigzag Traversal: ");
		treeTraversal.zigzagTraversal(btree.root);

		// verticalSum ()
		System.out.println("\nVertical Sum: ");
		btree.verticalSum(btree.root);

		// mirror ()
		System.out.println("\nMirrored Tree : ");
		TreeNode<Integer> mirrorTree = btree.mirror(btree.root);
		treeTraversal.printLevelOrderTraversal(mirrorTree);

		// findLCA ()
		System.out.println(
				"\nLCA is : " + btree.findLCA(btree.root, new TreeNode<Integer>(4), new TreeNode<Integer>(8)).key);

		// buildBinaryTreeFromInAndPreorderTraversal ()
		List<Integer> inorder = new ArrayList(Arrays.asList(new Integer[] { 4, 2, 5, 1, 6, 3, 7 }));
		List<Integer> preorder = new ArrayList(Arrays.asList(new Integer[] { 1, 2, 4, 5, 3, 6, 7 }));
		System.out.println("\nConstructed Tree: ");
		TreeNode<Integer> root = btree.buildBinaryTreeFromInAndPreorderTraversal(null, preorder, inorder);
		treeTraversal.printLevelOrderTraversal(root);
	}

}
