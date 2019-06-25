package com.kp.study.algorithms.old.trees;

import java.util.LinkedList;

import com.kp.study.algorithms.old.stacksandqueues.GenericQueue;
import com.kp.study.algorithms.old.trees.btree.BTree;

public class TreeTraversal {

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

    public void zigzagTraversal(TreeNode<Integer> root) {
        LinkedList<TreeNode<Integer>> currentStack = new LinkedList<TreeNode<Integer>>();
        LinkedList<TreeNode<Integer>> nextStack = null;

        boolean left = false;

        if (root == null) {
            System.err.println("Tree is empty.");
        }
        else {
            currentStack.add(root);

            do {
                nextStack = new LinkedList<TreeNode<Integer>>();
                while (!currentStack.isEmpty()) {
                    TreeNode<Integer> currentNode = currentStack.removeLast();

                    System.out.print(" " + currentNode.key + " ");

                    if (left) {
                        if (currentNode.left()) {
                            nextStack.add(currentNode.left);
                        }

                        if (currentNode.right()) {
                            nextStack.add(currentNode.right);
                        }
                    }
                    else {
                        if (currentNode.right()) {
                            nextStack.add(currentNode.right);
                        }

                        if (currentNode.left()) {
                            nextStack.add(currentNode.left);
                        }
                    }
                }

                // currentStack is exhausted. Move to the next lvl.
                if (!nextStack.isEmpty()) {
                    currentStack = nextStack;
                    left = !left;
                }
            }
            while (!nextStack.isEmpty());
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
