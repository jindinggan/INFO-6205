package week2;


public class Problems {
    static class ListNode {
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

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode temp = even;
        while (odd.next != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            if (odd.next != null) {
                even.next = odd.next;
                even = even.next;
            } else {
                even.next = null;
            }
        }
        odd.next = temp;
        return head;
    }

    public ListNode deleteNodes(ListNode head, int m, int n) {
        if (m == 0) {
            head = null;
            return head;
        }
        if (n == 0 || head == null) {
            return head;
        }
        ListNode temp = head;
        int count = m - 1;
        int toDelete = n;
        while (temp != null) {
            if (count == 0) {
                ListNode temp1 = temp;
                while (temp1.next != null && toDelete != 0) {
                    temp1 = temp1.next;
                    toDelete--;
                }
                toDelete = n;
                count = m - 1;
                temp.next = temp1.next;
                temp = temp.next;
            } else {
                temp = temp.next;
                count--;
            }
        }
        return head;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] result = new ListNode[k];
        ListNode temp = root;
        int size = 0;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        int rem = size % k;
        int lengthOfPart = size / k;
        ListNode temp1 = new ListNode(0, root);
        ListNode temp2 = root;
        int z = 0;
        for (int i = 0; i < k; i++) {

            if (rem > 0) {
                z = 1;
            }
            for (int b = 0; b < lengthOfPart + z; b++) {
                temp1 = temp1.next;

            }
            if (temp1 != null) {
                temp2 = temp1.next;
                temp1.next = null;
                result[i] = root;
                root = temp2;
                temp1.next = root;
            } else {
                result[i] = root;
            }

            rem--;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        splitListToParts(a, 5);
    }
}
