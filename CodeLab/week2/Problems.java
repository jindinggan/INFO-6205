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

    public int calculate(String s) {

        Stack<Integer> stack = new Stack<Integer>();
        int operand = 0;
        int result = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                operand = 10 * operand + (int) (ch - '0');

            } else if (ch == '+') {
                result += sign * operand;

                sign = 1;

                operand = 0;

            } else if (ch == '-') {

                result += sign * operand;
                sign = -1;
                operand = 0;

            } else if (ch == '(') {
                stack.push(result);
                stack.push(sign);

                sign = 1;
                result = 0;

            } else if (ch == ')') {

                result += sign * operand;

                result *= stack.pop();

                result += stack.pop();


                operand = 0;
            }
        }
        return result + (sign * operand);
    }

    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        int pos = 0;
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--cnt[s.charAt(i) - 'a'] == 0) break;
        }

        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }

    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                List<Character> decodedString = new ArrayList<>();

                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                }

                stack.pop();
                int base = 1;
                int k = 0;

                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop() - '0') * base;
                    base *= 10;
                }

                while (k != 0) {
                    for (int j = decodedString.size() - 1; j >= 0; j--) {
                        stack.push(decodedString.get(j));
                    }
                    k--;
                }
            }

            else {
                stack.push(s.charAt(i));
            }
        }

        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
    }

    public String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<Character>();

        for(char digit : num.toCharArray()) {
            while(stack.size() > 0 && k > 0 && stack.peekLast() > digit) {
                stack.removeLast();
                k -= 1;
            }
            stack.addLast(digit);
        }

        /* remove the remaining digits from the tail. */
        for(int i=0; i<k; ++i) {
            stack.removeLast();
        }

        // build the final string, while removing the leading zeros.
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        for(char digit: stack) {
            if(leadingZero && digit == '0') continue;
            leadingZero = false;
            ret.append(digit);
        }

        /* return the final string  */
        if (ret.length() == 0) return "0";
        return ret.toString();
    }

    public boolean find132pattern(int[] nums) {
        List <int[]> intervals = new ArrayList < > ();
        int i = 1, s = 0;
        while (i < nums.length) {
            if (nums[i] < nums[i - 1]) {
                if (s < i - 1)
                    intervals.add(new int[] {nums[s], nums[i - 1]});
                s = i;
            }
            for (int[] a: intervals)
                if (nums[i] > a[0] && nums[i] < a[1])
                    return true;
            i++;
        }
        return false;
    }

    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Stack <Integer> stack = new Stack<>();
        HashMap < Integer, Integer > map = new HashMap < > ();
        int[] res = new int[findNums.length];
        for (int i = 0; i < nums.length; i++) {
            while (!stack.empty() && nums[i] > stack.peek())
                map.put(stack.pop(), nums[i]);
            stack.push(nums[i]);
        }
        while (!stack.empty())
            map.put(stack.pop(), -1);
        for (int i = 0; i < findNums.length; i++) {
            res[i] = map.get(findNums[i]);
        }
        return res;
    }

    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }

    int i;
    public String countOfAtoms(String formula) {
        StringBuilder ans = new StringBuilder();
        i = 0;
        Map<String, Integer> count = parse(formula);
        for (String name: count.keySet()) {
            ans.append(name);
            int multiplicity = count.get(name);
            if (multiplicity > 1) ans.append("" + multiplicity);
        }
        return new String(ans);
    }

    public Map<String, Integer> parse(String formula) {
        int N = formula.length();
        Map<String, Integer> count = new TreeMap();
        while (i < N && formula.charAt(i) != ')') {
            if (formula.charAt(i) == '(') {
                i++;
                for (Map.Entry<String, Integer> entry: parse(formula).entrySet()) {
                    count.put(entry.getKey(), count.getOrDefault(entry.getKey(), 0) + entry.getValue());
                }
            } else {
                int iStart = i++;
                while (i < N && Character.isLowerCase(formula.charAt(i))) i++;
                String name = formula.substring(iStart, i);
                iStart = i;
                while (i < N && Character.isDigit(formula.charAt(i))) i++;
                int multiplicity = iStart < i ? Integer.parseInt(formula.substring(iStart, i)) : 1;
                count.put(name, count.getOrDefault(name, 0) + multiplicity);
            }
        }
        int iStart = ++i;
        while (i < N && Character.isDigit(formula.charAt(i))) i++;
        if (iStart < i) {
            int multiplicity = Integer.parseInt(formula.substring(iStart, i));
            for (String key: count.keySet()) {
                count.put(key, count.get(key) * multiplicity);
            }
        }
        return count;
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack();
        for (int ast: asteroids) {
            collision: {
                while (!stack.isEmpty() && ast < 0 && 0 < stack.peek()) {
                    if (stack.peek() < -ast) {
                        stack.pop();
                        continue;
                    } else if (stack.peek() == -ast) {
                        stack.pop();
                    }
                    break collision;
                }
                stack.push(ast);
            }
        }

        int[] ans = new int[stack.size()];
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.pop();
        }
        return ans;
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
