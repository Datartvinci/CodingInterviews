package datastruct;

/**
 * Created by John on 2016/10/16.
 */
public class ListNode {
    public int val;
    public ListNode next = null;

    public  ListNode(int val) {
        this.val = val;
    }
    public static ListNode asListNode(int[] values){
        if(values.length<1)return null;
        ListNode head=new ListNode(values[0]);
        ListNode node=head;
        for(int i=1;i<values.length;++i){
            node.next=new ListNode(values[i]);
            node=node.next;
        }
        return head;
    }
}

