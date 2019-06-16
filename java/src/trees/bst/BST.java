/**
 * 
 */
package trees.bst;

import java.util.List;

import trees.TreeNode;

/**
 * @author kolge
 *
 */
public class BST<T> {
	TreeNode<T> root;

	public BST() {
		super();
	}

	/**
	 * Find element in BST.
	 * 
	 * @param root
	 * @param key
	 * @return
	 */
	public TreeNode<T> find(TreeNode<T> root, T key) {
		if (root == null) {
			return null;
		} else {
			if (root.equalTo(key)) {
				return root;
			} else {
				if (root.lessThan(key)) {
					return find(root.right, key);
				} else {
					return find(root.left, key);
				}
			}
		}
	}

	/**
	 * Find minimum element in BST.
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode<T> findMin(TreeNode<T> root) {
		if (root == null) {
			return null;
		} else {
			if (root.left()) {
				return findMin(root.left);
			} else {
				return root;
			}
		}
	}

	/**
	 * Find maximum element in BST.
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode<T> findMax(TreeNode<T> root) {
		if (root == null) {
			return null;
		} else {
			if (root.right()) {
				return findMax(root.right);
			} else {
				return root;
			}
		}
	}

	/**
	 * Insert new element in the tree.
	 * 
	 * @param root
	 * @param data
	 * @return
	 */
	private TreeNode<T> insertNode(TreeNode<T> root, T data) {
		if (root == null) {
			root = new TreeNode<>(data);
			System.out.println("Insert: " + data);
		} else {
			if (root.greaterThan(data)) {
				root.left = insertNode(root.left, data);
			} else {
				root.right = insertNode(root.right, data);
			}

			root = balanceTree(root);
		}

		return root;
	}

	public void insert(T data) {
		this.root = insertNode(this.root, data);
	}

	/**
	 * Delete element recursively. Element will either be in left or right
	 * subtree of root. Find the element. Find the max element in the left
	 * subtree and replace with current element recursively.
	 * 
	 * @param root
	 * @param data
	 * @return
	 */
	public TreeNode<T> delete(TreeNode<T> root, T data) {
		if (root == null) {
			return null;
		} else {
			if (root.greaterThan(data)) {
				// Find in left subtree.
				root.left = delete(root.left, data);
			} else if (root.lessThan(data)) {
				// Find in right subtee.
				root.right = delete(root.right, data);
			} else {
				// This is the node to be deleted.
				if (!root.left() && !root.right()) {
					return null;
				} else {
					if (!root.left()) {
						return root.right;
					} else {
						root.key = findMax(root.left).key;
						root.left = delete(root.left, root.key);
					}
				}
			}

			return root;
		}
	}

	/**
	 * Find LCA of given two items. If root is between left and right, it is the
	 * LCA. If not, find LCA recursively in either the left or right subtree.
	 * 
	 * @param root
	 * @param left
	 * @param right
	 * @return
	 */
	public TreeNode<T> findLCA(TreeNode<T> root, T left, T right) {
		if (root == null) {
			return null;
		} else {
			if ((root.lessThan(left) && root.greaterThan(right))
					|| (root.greaterThan(left) && root.lessThan(right))) {
				return root;
			} else {
				if (root.lessThan(left)) {
					return findLCA(root.right, left, right);
				} else {
					return findLCA(root.left, left, right);
				}

			}

		}
	}

	/**
	 * Check if the given BST is valid. Check at every node recursively in the
	 * BST if (value >= min-value-in-subtree and value <= max-value-in-subtree).
	 * 
	 * @param root
	 * @return
	 */
	public boolean isBST(TreeNode<T> root, T min, T max) {
		if (root == null) {
			return true;
		} else {
			if ((root.lessThan(max) || root.equalTo(max))
					&& (root.greaterThan(min) || root.equalTo(min))) {
				return isBST(root.left, min, root.key)
						&& isBST(root.right, root.key, max);
			} else {
				return false;
			}
		}
	}

	/**
	 * Check if tree is BST using inorder traversal. Pass previous value to
	 * compare with current node.
	 * 
	 * @param root
	 * @param prev
	 * @return
	 */
	public boolean isInorderBST(TreeNode<T> root, T prev) {
		if (root == null) {
			return true;
		} else {
			return isInorderBST(root.left, prev) && root.greaterThan(prev)
					&& isInorderBST(root.right, root.key);
		}
	}

	/**
	 * Convert given sorted array into BST.
	 * 
	 * @param root
	 * @param sortedArray
	 */
	public TreeNode<T> sortedArrayToBST(List<T> sortedArray) {
		if (sortedArray.isEmpty()) {
			return null;
		} else {
			TreeNode<T> node;
			if (sortedArray.size() == 1) {
				node = new TreeNode<>(sortedArray.get(0));
			} else {
				Integer mid = (sortedArray.size() - 1) / 2;

				node = new TreeNode<>(sortedArray.get(mid));
				node.left = sortedArrayToBST(sortedArray.subList(0, mid));
				node.right = sortedArrayToBST(sortedArray.subList(mid + 1,
						sortedArray.size()));
			}

			return node;
		}
	}

	/**
	 * Find kth smallest element in the BST using inorder traversal.
	 * 
	 * @param root
	 * @param k
	 * @param counter
	 * @return
	 */
	public TreeNode<T> findKthSmallest(TreeNode<T> root, Integer k,
			List<T> counter) {
		if (root == null) {
			return null;
		} else {
			TreeNode<T> node;

			node = findKthSmallest(root.left, k, counter);
			if (node != null) {
				return node;
			}

			counter.add(root.key);

			if (counter.size() == k) {
				return root;
			} else {
				node = findKthSmallest(root.right, k, counter);
				if (node != null) {
					return node;
				} else {
					return null;
				}
			}
		}
	}

	/**
	 * Rotate given node to right.
	 * 
	 * @param node
	 */
	public TreeNode<T> rotateRight(TreeNode<T> node) {
		if (node != null) {

			if (node.left != null) {
				TreeNode<T> left = node.left;
				TreeNode<T> temp = left.right;

				left.right = node;
				node.left = temp;

				return left;
			}
		}

		return node;
	}

	/**
	 * Rotate given node to left.
	 * 
	 * @param node
	 */
	public TreeNode<T> rotateLeft(TreeNode<T> node) {
		if (node != null) {
			if (node.right != null) {
				TreeNode<T> right = node.right;
				TreeNode<T> temp = right.left;

				right.left = node;
				node.right = temp;

				return right;
			}
		}
		return node;
	}

	public boolean isBalanced(TreeNode<T> root) {
		if (root == null) {
			return true;
		} else {
			Integer leftHeight = height(root.left);
			Integer rightHeight = height(root.right);

			return (Math.abs(leftHeight - rightHeight) <= 1)
					&& isBalanced(root.left) && isBalanced(root.right);
		}
	}

	public Integer height(TreeNode<T> root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + Math.max(height(root.left), height(root.right));
		}
	}

	public Integer balanceFactor(TreeNode<T> root) {
		return (height(root.right) - height(root.left));
	}

	public TreeNode<T> balanceTree(TreeNode<T> root) {
		Integer balanceFactor = balanceFactor(root);

		if (balanceFactor < -1 || balanceFactor > 1) {
			System.out.println("Balancing: " + root);
		}

		if (balanceFactor < -1) {
			// Tree is left heavy. Rotate Right.
			if (balanceFactor(root.left) > 0) {
				System.out.println("Double Right Rotation");
				// Trees left subtree is right heavy.
				root.left = rotateLeft(root.left);
				root = rotateRight(root);
			} else {
				root = rotateRight(root);
			}
		} else if (balanceFactor > 1) {
			// Tree is right heavy. Rotate Left.
			if (balanceFactor(root.right) < 0) {
				System.out.println("Double Left Rotation");
				// Trees right subtree is left heavy.
				root.right = rotateRight(root.right);
				root = rotateLeft(root);
			} else {
				root = rotateLeft(root);
			}
		}

		return root;
	}

	public void copyNode(TreeNode<T> from, TreeNode<T> to) {
		if (from != null && to != null) {
			to.key = from.key;
			to.left = from.left;
			to.right = from.right;
		}
	}

	public static BST<Integer> getSampleIntegerBST() {
		BST<Integer> tree = new BST<>();

		tree.root = new TreeNode<>(14);

		tree.root.left = new TreeNode<Integer>(7);
		tree.root.left.left = new TreeNode<Integer>(3);
		tree.root.left.left.left = new TreeNode<Integer>(2);
		tree.root.left.left.right = new TreeNode<Integer>(4);
		tree.root.left.right = new TreeNode<Integer>(10);
		tree.root.left.right.left = new TreeNode<Integer>(8);
		tree.root.left.right.right = new TreeNode<Integer>(12);

		tree.root.right = new TreeNode<Integer>(21);
		tree.root.right.left = new TreeNode<Integer>(18);
		tree.root.right.left.left = new TreeNode<Integer>(16);
		tree.root.right.left.right = new TreeNode<Integer>(20);
		tree.root.right.right = new TreeNode<Integer>(25);// 25
		tree.root.right.right.left = new TreeNode<Integer>(22);
		tree.root.right.right.right = new TreeNode<Integer>(27);

		return tree;
	}

	public static BST<Integer> getSampleIntegerSmallBST() {
		BST<Integer> tree = new BST<>();

		tree.root = new TreeNode<>(14);

		tree.root.left = new TreeNode<Integer>(7);
		tree.root.left.left = new TreeNode<Integer>(3);
		tree.root.left.right = new TreeNode<Integer>(10);

		tree.root.right = new TreeNode<Integer>(21);

		return tree;
	}
}
