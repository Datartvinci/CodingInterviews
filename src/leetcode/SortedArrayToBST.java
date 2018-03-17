package leetcode;

import codeinterview.datastructure.TreeNode;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * <p>
 * Example:
 * <p>
 * Given the sorted array: [-10,-3,0,5,9],
 * <p>
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 */
public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int left = 0;
        int right = nums.length - 1;
        return construct(left, right, nums);
    }

    public TreeNode construct(int left, int right, int[] nums) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = construct(left, mid - 1, nums);
        node.right = construct(mid + 1, right, nums);
        return node;
    }
}
