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
        if (head == null || head.next == null) return;
        //找中点
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode nextHead = slow.next;
        slow.next = null;
        nextHead = reverse(nextHead);
        merge(head, nextHead);
    }

    public ListNode reverse(ListNode root) {
        ListNode vhead = new ListNode();
        while (root != null) {
            ListNode node = root;
            root = root.next;
            node.next = vhead.next;
            vhead.next = node;
        }
        return vhead.next;
    }

    public void merge(ListNode root1, ListNode root2) {
        while (root1 != null && root2 != null) {
            ListNode root1_tmp = root1.next;
            ListNode root2_tmp = root2.next;

            root1.next = root2;
            root1 = root1_tmp;

            root2.next = root1;
            root2 = root2_tmp;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


