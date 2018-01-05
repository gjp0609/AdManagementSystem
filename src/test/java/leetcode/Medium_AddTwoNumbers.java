package leetcode;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * eg: Input: (2 -> 4 -> 3) + (5 -> 6 -> 4), Output: 7 -> 0 -> 8, Explanation: 342 + 465 = 807.
 *
 * @author guojinpeng
 * @date 18.1.5 13:11
 */
public class Medium_AddTwoNumbers {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) throw new IllegalArgumentException("args should not all be null");
        ListNode node1 = l1;
        ListNode node2 = l2;
        int high = 0;
        while (true) {
            int sum = 0;
            if (node1 != null) sum += node1.val;
            if (node2 != null) sum += node2.val;
            int temp = sum / 10;
            sum += high;
            sum %= 10;
            node1 = node1 != null ? node1.next : null;
            node2 = node2 != null ? node2.next : null;
            high = temp;
            System.out.println(sum + " - " + high);
            if (false) break;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Medium_AddTwoNumbers addTwoNumbers = new Medium_AddTwoNumbers();
        ListNode node1 = getNode("999");
        ListNode node2 = getNode("1");
        ListNode listNode = addTwoNumbers.addTwoNumbers(node1, node2);
        System.out.println(getNumber(listNode));
    }

    public static String getString(ListNode node) {
        StringBuilder s = new StringBuilder();
        if (node != null)
            while (true) {
                s.append(node.val);
                node = node.next;
                if (node == null) {
                    break;
                } else {
                    s.append(" -> ");
                }
            }
        return s.toString();
    }

    public static String getNumber(ListNode node) {
        StringBuilder s = new StringBuilder();
        if (node != null) while (true) {
            s.insert(0, node.val);
            node = node.next;
            if (node == null) break;
        }
        return s.toString();
    }

    public static ListNode getNode(String number) {
        char[] chars = number.toCharArray();
        ListNode firstNode = null;
        ListNode iNode;
        ListNode rightNode = null;
        for (int i = chars.length - 1; i >= 0; i--) {
            int sum = chars[i] - '0';
            if (firstNode == null) {
                // 初始化第一个节点
                firstNode = new ListNode(sum);
                rightNode = firstNode;
            } else {
                ListNode node = new ListNode(sum);
                iNode = rightNode;
                iNode.next = node;
                rightNode = node;
            }
        }
        return firstNode;
    }
}
