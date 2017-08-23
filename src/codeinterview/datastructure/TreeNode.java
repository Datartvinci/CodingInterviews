package codeinterview.datastructure;

/**
 * Created by John on 2016/10/15.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode asTree(int[] pre,int[] in) {
        if (pre == null || in == null) return null;
        return constructCore(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }
    public static TreeNode constructCore(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (pre == null || preStart > preEnd || in == null || inStart > inEnd || pre.length <= 0 || in.length <= 0)
            return null;
        TreeNode root = new TreeNode(pre[preStart]);
        int rootIndex = inStart;
        while (in[rootIndex] != pre[preStart]) ++rootIndex;
        int leftLength = rootIndex - inStart;
        int rightLength = in.length - leftLength;
        if (leftLength > 0)
            root.left = constructCore(pre, preStart + 1, (preStart + 1) + (leftLength - 1), in, inStart, inStart + leftLength - 1);
        if (rightLength > 0)
            root.right = constructCore(pre, preStart + 1 + leftLength, preEnd, in, rootIndex + 1, inEnd);
        return root;
    }
    @Override
    public String toString() {
        //前序遍历打印二叉树
        StringBuilder sb = new StringBuilder();
        sb.append(this.val);
        if (left != null)
            sb.append(",").append(left.toString());
        if (right != null)
            sb.append(",").append(right.toString());
        return sb.toString();
    }
}