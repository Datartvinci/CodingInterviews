package codeinterview;

import datastructure.ListNode;
import datastructure.TreeNode;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by John on 2016/10/15.
 */
public class Solution {
    public static volatile Solution instance;

    public static Solution getInstance() {
        if (instance == null) instance = new Solution();
        return instance;
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
     *
     * @param sequence int[]
     * @return bool
     */
    public boolean VerifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        return judge(sequence, 0, sequence.length - 1);
    }

    private boolean judge(int[] sequence, int left, int right) {
        if (left >= right) return true;
        int i = left;
        //找到左节点
        while (i < right && sequence[i] < sequence[right]) ++i;

        int j = i;
        while (j < right) {
            ++j;
            if (sequence[j] < sequence[right]) return false;
        }
        return judge(sequence, left, i - 1) && judge(sequence, i, right - 1);
    }

    @Test
    public void testVerifySquenceOfBST() {
        assertTrue(getInstance().VerifySequenceOfBST(new int[]{2, 4, 3, 6, 8, 7, 5}));
        assertTrue(!getInstance().VerifySequenceOfBST(new int[]{7, 4, 6, 5}));
        assertTrue(!getInstance().VerifySequenceOfBST(new int[]{}));
    }

    /**
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        return list;
    }

    @Test
    public void testPrintFromTopToBottom() {
        TreeNode root = TreeNode.asTree(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 2, 5, 1, 6, 3, 7});
        System.out.println(Arrays.toString(getInstance().printFromTopToBottom(root).toArray()));
    }

    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。
     * 例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，
     * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。
     * （注意：这两个序列的长度是相等的）
     *
     * @param pushA
     * @param popA
     * @return
     */
    public boolean isPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null) return false;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int j = 0;
        while (j < popA.length) {
            while (!stack.empty() && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
            if (i < pushA.length)
                stack.push(pushA[i++]);
            if (i == pushA.length && !stack.empty() && stack.peek() != popA[j]) return false;
        }
        return true;

    }

    @Test
    public void testIsPopOrder() {
        assertTrue(getInstance().isPopOrder(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
    }

    /**
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
     */
    Stack<Integer> dataStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void Push(int node) {
        dataStack.push(node);
        if (minStack.empty() || minStack.peek() > node)
            minStack.push(node);
        else minStack.push(minStack.peek());
    }

    public void Pop() {
        if (!dataStack.empty() && !minStack.empty()) {
            dataStack.pop();
            minStack.pop();
        }
    }

    public int top() {
        if (!dataStack.empty()) {
            return dataStack.peek();
        }
        return -1;
    }

    public int min() {
        if (minStack.empty()) return -1;
        return minStack.peek();
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
     * 例如，如果输入如下矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     * PS：调试了3次才通过，没考虑这个矩阵的长和宽不一样以及长度为1和高度为1这三种情况。
     *
     * @param matrix
     * @return 输出顺序的array list
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) return null;
        int start = 0;
        ArrayList<Integer> list = new ArrayList<>(matrix.length * matrix[0].length);
        //当n=4，最内圈的起始点是（1，1），当n=5，最内圈的起始点是（2，2），也就是说最内圈的起始点是小于矩阵长度一半的。
        while (start * 2 < matrix.length && start * 2 < matrix[0].length) {
            int top, left, bottom, right;
            left = top = start;
            bottom = matrix.length - 1 - top;
            right = matrix[0].length - 1 - left;
            //从左到右
            for (int i = left; i <= right; ++i) {
                if (i > right) break;
                list.add(matrix[top][i]);
            }
            //从上到下
            for (int i = top + 1; i <= bottom; ++i) {
                if (i > bottom) break;
                list.add(matrix[i][right]);
            }
            //从右到左
            for (int i = right - 1; i >= left; --i) {
                if (i < left || bottom == top) break;
                list.add(matrix[bottom][i]);
            }
            //从下到上
            for (int i = bottom - 1; i > top; --i) {
                if (i < top || left == right) break;
                list.add(matrix[i][left]);
            }
            ++start;
        }
        return list;
    }

    @Test
    public void testPrintMatrix() {
        //1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
        //     System.out.println( Arrays.toString(getInstance().printMatrix(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}).toArray()));
        System.out.println(Arrays.toString(getInstance().printMatrix(new int[][]{{1}, {2}, {3}, {4}, {5}}).toArray()));

    }

    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     * 可以用递归的方法，对当前节点的左右节点进行交换，然后再对左右节点调用mirror函数
     *
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root == null) return;
        if (root.left != null && root.right != null) {
            //swap and recurse
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            Mirror(root.left);
            Mirror(root.right);
        } else if (root.left != null) {
            root.right = root.left;
            root.left = null;
            Mirror(root.right);
        } else if (root.right != null) {
            root.left = root.right;
            root.right = null;
            Mirror(root.left);
        }
    }

    /**
     * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root1 != null && root2 != null) {
            //前序遍历的方式
            if (root1.val == root2.val) {
                result = doesRoot1HasRoot2(root1, root2);
            }
            if (!result)
                result = HasSubtree(root1.left, root2);
            if (!result)
                result = HasSubtree(root1.right, root2);
        }
        return result;
    }

    private boolean doesRoot1HasRoot2(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val != root2.val) return false;
        return doesRoot1HasRoot2(root1.left, root2.left) && doesRoot1HasRoot2(root1.right, root2.right);

    }

    @Test
    public void testHasSubtree() {
        TreeNode root2 = TreeNode.asTree(new int[]{3, 6, 7}, new int[]{6, 3, 7});
        TreeNode root1 = TreeNode.asTree(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 2, 5, 1, 6, 3, 7});
        assertEquals(true, getInstance().HasSubtree(root1, root2));
    }

    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     * 使用递归的思想
     *
     * @param list1
     * @param list2
     * @return 两个链表合成后的链表
     */
    public ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;
        if (list1 == null) return list2;
        else if (list2 == null) return list1;
        ListNode mergeHead;
        if (list1.val < list2.val) {
            mergeHead = list1;
            mergeHead.next = merge(list1.next, list2);
        } else {
            mergeHead = list2;
            mergeHead.next = merge(list1, list2.next);
        }
        return mergeHead;

    }

    @Test
    public void testMerge() {
        ListNode list1 = ListNode.asListNode(new int[]{1, 3, 5});
        ListNode list2 = ListNode.asListNode(new int[]{2, 4, 6});
        ListNode head = merge(list1, list2);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    /**
     * 输入一个链表，反转链表后，输出链表的所有元素。
     *
     * @param head
     * @return head
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode prevNode = null;
        ListNode curNode = head;
        ListNode postNode = head.next;
        while (curNode != null) {
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = postNode;
            if (postNode != null)
                postNode = postNode.next;
        }
        return prevNode;

    }

    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     *
     * @param head
     * @param k
     * @return 倒数第k个结点
     */
    public ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k < 0) return null;
        ListNode postNode = head;
        while (--k > 0) {
            if (postNode.next == null) break;
            postNode = postNode.next;
        }
        if (k != 0) return null;
        while (postNode.next != null) {
            postNode = postNode.next;
            head = head.next;
        }
        return head;
    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     *
     * @param base
     * @param exponent
     * @return power
     */
    public double power(double base, int exponent) {
        if (base == 0) return 0;
        if (exponent == 0) return 1;

        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }
        double result = base;
        while (--exponent > 0) {
            result *= base;
        }
        return result;
    }

    @Test
    public void testPower() {
        assertEquals(getInstance().power(2, 3), 8, 0.000001);
    }

    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     *
     * @param n
     * @return 二进制中1的个数
     */
    public int NumberOf1(int n) {
        int result = 0;
        while (n != 0) {
            n = (n - 1) & n;
            ++result;
        }
        return result;
    }

    @Test
    public void testNumberOf1() {
        assertEquals(1, getInstance().NumberOf1(1));
        assertEquals(32, getInstance().NumberOf1(0xFFFFFFFF));
        assertEquals(1, getInstance().NumberOf1(0x80000000));
    }

    public Solution() {
        Arrays.fill(occurence, -1);
    }

    /**
     * 一个链表中包含环，请找出该链表的环的入口结点。
     *
     * @param pHead
     * @return 环的入口节点
     */
    public ListNode entryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) return null;
        ListNode prevNode = pHead;
        ListNode postNode = pHead.next;
        while (prevNode != postNode) {
            prevNode = prevNode.next;
            postNode = postNode.next.next;
        }
        int count = 1;
        postNode = postNode.next;
        while (postNode != prevNode) {
            ++count;
            postNode = postNode.next;
        }
        postNode = prevNode = pHead;
        while (count-- > 0) {
            postNode = postNode.next;
        }
        while (prevNode != postNode) {
            prevNode = prevNode.next;
            postNode = postNode.next;
        }
        return postNode;
    }

    @Test
    public void testEntryNodeOfLoop() {
        //生成一个有环的链表
        ListNode root = new ListNode(1);
        ListNode current = root;
        for (int i = 2; i <= 5; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }
        current.next = root;

        assertEquals(root, getInstance().entryNodeOfLoop(root));
        assertEquals(null, getInstance().entryNodeOfLoop(null));
    }


    int[] occurence = new int[256];
    int index = 0;

    //Insert one char from stringstream
    public void insert(char ch) {
        if (occurence[ch] == -1) {
            occurence[ch] = index;
        } else if (occurence[ch] >= 0) {
            occurence[ch] = -2;
        }
        ++index;
    }
    //return the first appearence once char in current stringstream

    /**
     * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，
     * 第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
     * <p>
     * 一共有256个字符，每个字符对应int数组的下标，用-1表示没出现过，用-2表示出现过两次，只出现一次则存储下标。
     * 寻找的时候，找到下标最小的正数，那个数就是位置，意思就是最靠前的位置，
     *
     * @return 如果当前字符流没有存在出现一次的字符，返回#字符。
     */
    public char firstAppearingOnce() {
        char result = '#';
        int minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < 256; ++i) {
            if (occurence[i] >= 0 && occurence[i] < minIndex) {
                minIndex = occurence[i];
                result = (char) i;
            }
        }
        return result;
    }

    @Test
    public void testFirstAppearingOnce() {
        getInstance().insert('g');
        getInstance().insert('o');
        getInstance().insert('o');
        getInstance().insert('g');
        getInstance().insert('l');
        assertEquals('l', getInstance().firstAppearingOnce());
    }


    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
     * 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * 解法：1.利用冒泡排序的思想，从尾到头遍历，如果是偶数，则偶数后面的数组前移，偶数插入，时间复杂度O(n2),空间复杂度O(1)
     * 2.新开辟一个数组，旧数组用两个指针扫描，时间复杂度O(n）,空间复杂度O(n)
     *
     * @param array
     */
    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0) return;
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--)

            {
                //奇数前面只能是奇数或者偶数，如果是偶数，就交换位置，最终一定能把奇数冒泡到顶层
                //找到第一个奇数，它的前面恰好是偶数的，然后和偶数交换位置
                if (array[j] % 2 == 1 && array[j - 1] % 2 == 0) //前偶后奇交换
                {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }

    }

    @Test
    public void testReOrderArray() {
        Solution solution = getInstance();
        int[] data = new int[]{2, 4, 6, 1, 3, 5};
        solution.reOrderArray(data);
        assertArrayEquals(data, new int[]{1, 3, 5, 2, 4, 6});
        int[] data2 = new int[]{};
        solution.reOrderArray(data2);
        assertArrayEquals(data2, new int[]{});
    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     *
     * @param node
     */
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        int result = -1;
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        if (!stack2.empty())
            result = stack2.pop();
        return result;
    }

    @Test
    public void testQueue() {
        getInstance().push(1);
        getInstance().push(2);
        getInstance().push(3);
        System.out.println(getInstance().pop());
        System.out.println(getInstance().pop());
        getInstance().push(4);
        System.out.println(getInstance().pop());
        getInstance().push(5);
        System.out.println(getInstance().pop());
        System.out.println(getInstance().pop());
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     *
     * @param pre 前序遍历
     * @param in  中序遍历
     * @return
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null) return null;
        return TreeNode.constructCore(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }


    @Test
    public void testReConstructBinaryTree() {
        Solution solution = getInstance();
        assertEquals("1,2,4,7,3,5,6,8", solution.reConstructBinaryTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6}).toString());
    }

    /**
     * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     * <p>
     * 如果从前往后替换，每次都要重复移动后面的字符串，所以应该先算好长度，再从后开始
     *
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        if (str == null || str.length() == 0) return null;
        return str.toString().replaceAll(" ", "%20");
    }

    @Test
    public void testReplaceSpace() {
        StringBuffer stringBuffer = new StringBuffer("i am a student.");
        assertEquals("i%20am%20a%20student.", getInstance().replaceSpace(stringBuffer));
        assertEquals(null, getInstance().replaceSpace(null));
    }

    /**
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p>
     * 利用数组的特性一次排除一行。
     * PS:一开始没通过，是因为循环条件那里写错成y>0了
     *
     * @param array
     * @param target
     * @return true if there is a target in the array
     */
    public boolean find(int[][] array, int target) {
        if (array == null || array.length == 0 || array[0].length == 0) return false;
        int x = 0;
        int y = array[0].length - 1;
        while (x < array.length && y >= 0) {
            if (target == array[x][y]) {
                return true;
            } else if (target > array[x][y]) ++x;
            else --y;
        }
        return false;
    }

    @Test
    public void testFind() {
        assertTrue(getInstance().find(new int[][]{{1, 2, 3, 4}, {3, 4, 5, 6}, {4, 5, 6, 7}, {5, 6, 7, 8}}, 5));
        assertFalse(getInstance().find(new int[][]{}, 5));
        assertFalse(getInstance().find(new int[][]{{}}, 5));
    }

    public void reverse(byte[] bytes) {
        for (int i = 0, j = bytes.length - 1; i < bytes.length; i++) {

        }
    }
}
