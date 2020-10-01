package week2;


import java.util.HashMap;
import java.util.Stack;

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
        ListNode temp1 = root;
        int size = 0;
        while (temp1 != null) {
            temp1 = temp1.next;
            size++;
        }
        ListNode temp = root;
        int rem = size % k;
        int lengthOfPart = size / k;
        for (int i = 0; i < k; i++) {
            root = temp;
            for (int j = 0; j < lengthOfPart + (rem > 0 ? 1 : 0) - 1; j++) {
                temp = temp.next;
            }
            if (temp != null) {
                ListNode temp2 = temp.next;
                temp.next = null;
                temp = temp2;
            }
            result[i] = root;
            rem--;
        }
        return result;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = new ListNode(0, head);
        ListNode fast = head;
        ListNode copy = slow;
        int count = 0;
        while (fast != null) {
            if (fast.next == null) {
                if (count != 0) {
                    slow.next = null;
                }
            } else if (fast.next.val == fast.val) {
                count++;
            } else if (fast.next.val != fast.val && count != 0) {
                slow.next = fast.next;
                count = 0;
            } else {
                count = 0;
                slow = fast;
            }
            fast = fast.next;
        }
        return copy.next;
    }
    // TODO: Problem 6

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = slow;


        // reverse second half
        ListNode prev = null;
        ListNode cur = secondHalf;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
            System.out.println(prev.val);
        }
        ListNode reversed = prev;
        ListNode result = new ListNode(0);
        ListNode first = head;
        ListNode temp = head;
        while (reversed.next != null) {
            temp = first.next;
            first.next = reversed;
            first = temp;
            temp = reversed.next;
            reversed.next = first;
            reversed = temp;
        }
    }
    // TODO: Problem 8

    public ListNode swapPairs(ListNode head) {
        ListNode temp = new ListNode(0, head);
        ListNode result = temp;
        while (temp.next != null && temp.next.next != null) {
            ListNode current = temp.next;
            ListNode current2 = temp.next.next;
            temp.next = current2;
            current.next = temp.next.next;
            temp.next.next = current;
            temp = temp.next.next;
        }
        return result.next;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode temp = new ListNode(0, head);
        ListNode result = temp;
        while (temp != null && temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }

        }
        return result.next;
    }

    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char c = stack.pop();
                    if (map.get(s.charAt(i)) != c) {
                        return false;
                    }
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    public String simplifyPath(String path) {
        if (path.length() == 0) {
            return path;
        }
        String[] parts = path.split("/");
        Stack<String> stack = new Stack<String>();
        for (String s : parts) {
            if (s.length() == 0 || s.equals(".")) {
                continue;
            } else if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(s);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (String str : stack) {
            builder.append("/");
            builder.append(str);
        }
        return builder.length() > 0 ? builder.toString() : "/";
    }

    class MinStack {

        private Stack<Integer> stack = new Stack<Integer>();
        private Stack<Integer> min = new Stack<Integer>();

        public MinStack() {

        }

        public void push(int x) {
            stack.push(x);
            if (min.isEmpty() || x <= min.peek()) {
                min.push(x);
            }
        }

        public void pop() {
            if (stack.peek().equals(min.peek())) {
                min.pop();
            }
            this.stack.pop();
        }

        public int top() {
            return this.stack.peek();
        }

        public int getMin() {
            return min.peek();
        }
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
