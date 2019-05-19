package leetcode;

import datastructure.TreeNode;

public class SameTree {

    public static void main(String[] args) {
        TreeNode rootA=new TreeNode(1);
        rootA.left=new TreeNode(2);
        rootA.right=new TreeNode(3);
        TreeNode rootB=new TreeNode(1);
        rootB.left=new TreeNode(2);
        rootB.right=new TreeNode(3);
        System.out.println( isSameTree(rootA,rootB));;
    }
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p !=null&& q==null||p==null&&q!=null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
