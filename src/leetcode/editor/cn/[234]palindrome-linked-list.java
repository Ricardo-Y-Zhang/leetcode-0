//请判断一个链表是否为回文链表。 
//
// 示例 1: 
//
// 输入: 1->2
//输出: false 
//
// 示例 2: 
//
// 输入: 1->2->2->1
//输出: true
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 
// 👍 1063 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode mid = findMid(head);
        ListNode temp = reverseList(mid.next);
        ListNode node1 = head, node2 = temp;
        boolean flag = true;
        while (node1 != null && node2 != null) {
            if (node1.val != node2.val) {
                flag = false;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        reverseList(temp);
        return flag;
    }
    public ListNode findMid(ListNode head) {//返回链表的中点，偶数返回靠左的中点
        ListNode vhead = new ListNode(0, head);
        ListNode fast = vhead, slow = vhead;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newhead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newhead;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

