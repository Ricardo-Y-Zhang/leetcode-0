//给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 104] 内 
// -105 <= Node.val <= 105 
// 
//
// 
//
// 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
//
// 
//
// 注意：本题与主站 148 题相同：https://leetcode-cn.com/problems/sort-list/ 
// Related Topics 链表 双指针 分治 排序 归并排序 
// 👍 28 👎 0


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
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }
    public ListNode sortList(ListNode head, ListNode tail){
        if (head == null){//该子链 0 个节点
            return head;
        }
        if (head.next == tail){//该子链 1 个节点
            head.next = null;
            return head;
        }
        //快慢指针找中点
        ListNode slow = head, fast = head;
        while (fast != tail){
            slow = slow.next;
            fast = fast.next;
            if (fast != tail){
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        //递归对两个子链表排序
        ListNode node1 = sortList(head, mid);
        ListNode node2 = sortList(mid, tail);
        //两个子链表合并
        ListNode node = merge(node1, node2);
        return node;
    }

    public ListNode merge(ListNode node1, ListNode node2){
        ListNode vhead = new ListNode();
        ListNode node = vhead;
        while (node1 != null && node2 != null){
            if (node1.val < node2.val){
                node.next = node1;
                node1 = node1.next;
                node = node.next;
            }else{
                node.next = node2;
                node2 = node2.next;
                node = node.next;
            }
        }
        if (node1 != null){
            node.next = node1;
        }
        if (node2 != null){
            node.next = node2;
        }
        return vhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


