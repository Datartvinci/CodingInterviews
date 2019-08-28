package leetcode;

public class ConnectTreeNode {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node result = root;
        while (root != null) {
            Node node = root;
            boolean first = true;
            Node last = new Node();
            while (node != null) {
                if (node.left != null) {
                    last.next = node.left;
                    last = last.next;
                    if (first) {
                        root = last;
                        first = false;
                    }
                }
                if (node.right != null) {
                    last.next = node.right;
                    last = last.next;
                    if (first) {
                        root = last;
                        first = false;
                    }
                }
                node = node.next;
                if (first && node == null) {
                    root = null;
                    first = false;
                }
            }
        }
        return result;
    }


    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;


}
