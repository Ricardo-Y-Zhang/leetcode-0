//给定一个链表的 头节点 head ，请判断其是否为回文链表。 
//
// 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入: head = [1,2,3,3,2,1]
//输出: true 
//
// 示例 2： 
//
// 
//
// 
//输入: head = [1,2]
//输出: false
// 
//
// 
//
// 提示： 
//
// 
// 链表 L 的长度范围为 [1, 105] 
// 0 <= node.val <= 9 
// 
//
// 
//
// 进阶：能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
//
// 
//
// 注意：本题与主站 234 题相同：https://leetcode-cn.com/problems/palindrome-linked-list/ 
// Related Topics 栈 递归 链表 双指针 
// 👍 23 👎 0


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
    public boolean isPalindrome(ListNode head) {
        if (head == null){
            return true;
        }
        ListNode mid = findMid(head);
        ListNode temp = reverse(mid.next);
        ListNode node1 = head, node2 = temp;
        boolean flag = true;
        while (node1 != null && node2 != null){
            if (node1.val != node2.val){
                flag = false;
                break;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        reverse(temp);
        return flag;
    }
    public ListNode findMid(ListNode head){//找中间节点
        ListNode vhead = new ListNode(0, head);
        ListNode fast = vhead, slow = vhead;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    public ListNode reverse(ListNode head){
        ListNode vhead = new ListNode(0, null);
        while (head != null){
            ListNode temp = head;
            head = head.next;
            temp.next = vhead.next;
            vhead.next = temp;
        }
        return vhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


