package week1;

public class Day2 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public ListNode removeNthNode(ListNode head, int k) {
        ListNode temp = new ListNode(0, head);
        ListNode slow = temp;
        ListNode fast = temp;
        for (int i = 0; i < k + 1; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return temp.next;
    }
}
