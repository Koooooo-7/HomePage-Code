package com.koy.cases.leetcode02;

/**
 * @author Koy  https://github.com/Koooooo-7
 * @Description
 */

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 结果链表
        ListNode result = null;
        // 上一个Node，用来和新生成的Node链接
        ListNode lastNode = null;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        // 需要向后面需要进位, 0/1
        int carry = 0;
        while (cur1 != null || cur2 != null) {
            // 如果l1比l2短，那就用0补齐
            if (cur1 == null) {
                cur1 = new ListNode(0);
            }
            // 如果l2比l1短，那就用0补齐
            if (cur2 == null) {
                cur2 = new ListNode(0);
            }

            int val1 = cur1.val;
            int val2 = cur2.val;
            // val [0,19]
            int val = val1 + val2 + carry;
            if (val >= 10) {
                val = val % 10;
                carry = 1;
            }else {
                carry = 0;
            }

            // 第一次进来
            if (result == null) {
                result = new ListNode(val);
                lastNode = result;

            } else {
                ListNode next = new ListNode(val);
                lastNode.next = next;
                lastNode = next;
            }

            cur1 = cur1.next;
            cur2 = cur2.next;

        }

        // 如果最后还有一次进位值，直接补上
        if (carry != 0) {
            lastNode.next = new ListNode(carry);
        }
        return result;
    }
}
