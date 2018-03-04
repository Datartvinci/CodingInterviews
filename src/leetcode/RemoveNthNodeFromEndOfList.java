package leetcode;

import java.util.Stack;

/**
 * Created by Datartvinci on 2017/8/26.
 * Given a linked list, remove the nth node from the end of list and return its head.
 */
public class RemoveNthNodeFromEndOfList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        new RemoveNthNodeFromEndOfList().removeNthFromEnd(head, 1);
        System.out.println(new RemoveNthNodeFromEndOfList().isValid("(){}"));;
    }

    /**
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return partition(lists,0,lists.length-1);
    }
    public ListNode partition(ListNode[] lists,int left,int right){
        if(left==right){
            return lists[left];
        }
        if(left<right){
            int mid=(left+right)/2;
            ListNode l1=partition(lists,left,mid);
            ListNode l2=partition(lists,mid+1,right);
            return merge(l1,l2);
        }else{
            return null;
        }

    }
    public ListNode merge(ListNode l1,ListNode l2){
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        if(l1.val<l2.val){
            l1.next=merge(l1.next,l2);
            return l1;
        }else{
            l2.next=merge(l2.next,l1);
            return l2;
        }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start=new ListNode(0);
        start.next=head;
        ListNode endNode = start;
        ListNode destNode=start;
        for (int i = 0; i <= n; i++) {
            endNode = endNode.next;
        }
        while (endNode != null) {
            endNode = endNode.next;
            destNode = destNode.next;
        }

        destNode.next = destNode.next.next;

        return start.next;
    }
    public boolean isValid(String s) {
        Stack<Character> stack=new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='('||c=='['||c=='{'){
                stack.push(c);
            }else {
                if(stack.isEmpty()){
                    return false;
                }
                Character left = stack.pop();
                if(!match(left,c)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    private boolean match(char left,char right){
        return left=='{'&&right=='}'||left=='['&&right==']'||left=='('&&right==')';

    }
}
