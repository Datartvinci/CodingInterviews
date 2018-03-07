package leetcode;

public class HasCycle {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null && slow.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * <1> 定义两个指针p1和p2，在初始化时都指向链表的头节点。
     * <2> 如果链表中的环有n个节点，指针p1先在链表上向前移动n步。
     * <3> 然后指针p1和p2以相同的速度在链表上向前移动直到它们相遇。
     * <4> 它们相遇的节点就是环的入口节点。
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        int size = -1;
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null && slow.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                size = 1;
                slow = slow.next;
                while (slow != fast) {
                    size++;
                    slow = slow.next;
                }
                break;
            }
        }
        if (size == -1) {
            return null;
        }
        fast = head;
        slow = head;
        for (int i = 0; i < size; i++) {
            fast=fast.next;
        }
        while (slow != fast) {
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
}
