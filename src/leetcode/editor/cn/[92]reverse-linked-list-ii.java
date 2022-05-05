//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [5], left = 1, right = 1
//输出：[5]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目为 n 
// 1 <= n <= 500 
// -500 <= Node.val <= 500 
// 1 <= left <= right <= n 
// 
//
// 
//
// 进阶： 你可以使用一趟扫描完成反转吗？ 
// Related Topics 链表 
// 👍 1056 👎 0


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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) return head;
        ListNode vhead = new ListNode(-1, head);//虚拟头节点，无需特判left=1的情况
        //找到第left-1个节点
        ListNode pre = vhead;
        for (int i = 0; i < left-1; i++) {
            pre = pre.next;
        }
        ListNode lnode = pre.next;//第left个节点，作为反转链表段的尾节点
        //头插法反转链表[left, right]
        ListNode node = pre.next;
        for (int i = 0; i < right - left+1; i++) {
            ListNode temp = node;
            node = node.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        //连接第left节点和第right+1节点
        lnode.next = node;
        return vhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


