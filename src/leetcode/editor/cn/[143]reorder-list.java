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
        if (head == null || head.next == null){
            return;
        }

        //记录链表长度
        int length = 0;
        ListNode now = head;
        while (now != null){
            now = now.next;
            length++;
        }

        //记录链表的中间节点
        ListNode tail = head;
        for (int i = 0; i < (length-1)/2; i++) {
            tail = tail.next;
        }

        //将链表的后半节点翻转并组成新链表
        ListNode head2 = new ListNode();
        head2.next = null;//虚拟头节点
        now = tail.next;
        tail.next = null;//链表中间节点next置为null
        while (now != null){//头插法翻转链表
            ListNode temp = now;
            now = now.next;
            temp.next = head2.next;
            head2.next = temp;
        }

        //将翻转后的新链表插入前半节点中
        head2 = head2.next;
        now = head;
        while (head2 != null){
            ListNode temp1 = now, temp2 = head2;
            now = now.next;
            head2 = head2.next;
            temp2.next = temp1.next;
            temp1.next = temp2;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


