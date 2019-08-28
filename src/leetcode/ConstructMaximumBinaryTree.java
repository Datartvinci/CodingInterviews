package leetcode;

import datastructure.TreeNode;

/**
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 *
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 */
public class ConstructMaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums){
        return cons(nums,0,nums.length-1);
    }
    public TreeNode cons(int[] nums,int start,int end) {
        if (start > end) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        int mid=0;
        for (int i = start; i <= end; i++) {
            int x = nums[i];
            if (x > max) {
                max=x;
                mid=i;
            }
        }
        TreeNode node=new TreeNode(max);
        node.left=cons(nums,start,mid-1);
        node.right=cons(nums,mid+1,end);
        return node;
    }
}
