package leetcode;

import codeinterview.datastructure.TreeNode;

import java.util.*;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its bottom-up level order traversal as:
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = TreeNode.asTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(root.toString());
        new BinaryTreeLevelOrderTraversal().levelOrderBottom(root)
                .forEach(list -> {
                    list.forEach(value -> System.out.print(value + ","));
                    System.out.println();
                });

    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        queue1.offer(root);
        Queue<TreeNode> queue2 = new LinkedList<>();
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            while (!queue1.isEmpty()) {
                TreeNode node = queue1.poll();
                temp.add(node.val);
                if (node.left != null)
                    queue2.offer(node.left);
                if (node.right != null)
                    queue2.offer(node.right);
            }
            if (!temp.isEmpty())
                result.add(temp);
            temp = new ArrayList<>();
            while (!queue2.isEmpty()) {
                TreeNode node = queue2.poll();
                temp.add(node.val);
                if (node.left != null)
                    queue1.offer(node.left);
                if (node.right != null)
                    queue1.offer(node.right);
            }
            if (!temp.isEmpty())
                result.add(temp);
        }
        Collections.reverse(result);
        return result;
    }

}
