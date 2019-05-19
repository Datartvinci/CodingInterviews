package leetcode;

import datastructure.TreeNode;

public class FlattenBinaryTreeLinkedList {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(5);
        node.left.left=new TreeNode(3);
        node.left.right=new TreeNode(4);
        node.right.right=new TreeNode(6);
        new FlattenBinaryTreeLinkedList().flatten(node);
    }

    public void flatten(TreeNode root) {
        rec(null, root);
        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }

    TreeNode rec(TreeNode parent, TreeNode node) {
        if (node == null) {
            return parent;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (parent != null) {
            node.left = null;
            parent.right = node;
        }
        node.right = left;
        //is leaf
        if (left == null && right == null) {
            return node;
        }
        TreeNode leftTail = rec(node, left);
        TreeNode rightTail = rec(leftTail, right);
        return rightTail;
    }
}
