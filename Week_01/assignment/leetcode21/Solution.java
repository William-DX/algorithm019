package assignment.leetcode21;

/**
 * @ClassName Solution
 * @Description 合并两个有序链表
 * @Author duxiao
 * @Date 2020/11/8 18:07
 * @Version 1.0
 **/
public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        /** loop */
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }

        pre.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
