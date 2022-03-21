//给定一个单链表 L 的头节点 head ，单链表 L 表示为： 
//
// L0 → L1 → … → Ln-1 → Ln 
//请将其重新排列后变为： 
//
// L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → … 
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: head = [1,2,3,4]
//输出: [1,4,2,3] 
//
// 示例 2: 
//
// 
//
// 
//输入: head = [1,2,3,4,5]
//输出: [1,5,2,4,3] 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 5 * 104] 
// 1 <= node.val <= 1000 
// 
// Related Topics 栈 递归 链表 双指针 
// 👍 693 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        //找链表中点
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //翻转后半链表
        ListNode head2 = reverse(slow.next);
        slow.next = null;
        //合并链表
        ListNode node1 = head, node2 = head2;
        while (node1 != null && node2 != null) {
            ListNode temp_node1 = node1.next;
            ListNode temp_node2 = node2.next;
            node1.next = node2;
            node1 = temp_node1;
            node2.next = node1;
            node2 = temp_node2;
        }
    }
    public ListNode reverse(ListNode head) {
        ListNode vhead = new ListNode(-1, null);
        while (head != null) {
            ListNode node = head;
            head = head.next;
            node.next = vhead.next;
            vhead.next = node;
        }
        return vhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


