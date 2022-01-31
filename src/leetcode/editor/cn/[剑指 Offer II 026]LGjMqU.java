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
//
// 
//
// 注意：本题与主站 143 题相同：https://leetcode-cn.com/problems/reorder-list/ 
// Related Topics 栈 递归 链表 双指针 
// 👍 26 👎 0


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
        int length = 0;
        ListNode node = head;
        while (node != null){
            length++;
            node = node.next;
        }
        if (length <= 2){
            return;
        }

        //链表中点
        ListNode mid = head;
        for (int i = 0; i < (length+1)/2; i++) {
            ListNode temp = mid;
            mid = mid.next;
            if (i == (length+1)/2-1){
                temp.next = null;
            }
        }

        mid = reverse(mid);

        //链表合并
        ListNode node1 = head, node2 = mid;
        for (int i = 0; i < length / 2; i++) {
            ListNode temp1 = node1, temp2 = node2;
            node1 = node1.next;
            node2 = node2.next;
            temp2.next = node1;
            temp1.next = temp2;
        }
    }
    public ListNode reverse(ListNode temp){//反转链表
        ListNode vhead = new ListNode();
        vhead.next = null;
        while (temp != null){
            ListNode node = temp;
            temp = temp.next;
            node.next = vhead.next;
            vhead.next = node;
        }
        return vhead.next;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


