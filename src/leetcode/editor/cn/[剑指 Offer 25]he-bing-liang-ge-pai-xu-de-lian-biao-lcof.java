//输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。 
//
// 示例1： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4 
//
// 限制： 
//
// 0 <= 链表长度 <= 1000 
//
// 注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/ 
// Related Topics 递归 链表 
// 👍 189 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp1 = l1, temp2 = l2, vhead = new ListNode(), tail = vhead;
        while (temp1 != null && temp2 != null){
            if (temp1.val <= temp2.val){
                tail.next=temp1;
                tail=tail.next;
                temp1=temp1.next;
            }else{
                tail.next=temp2;
                tail=tail.next;
                temp2=temp2.next;
            }
        }
        if (temp1 != null){
            tail.next=temp1;
        }
        if (temp2 != null){
            tail.next=temp2;
        }
        return vhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


