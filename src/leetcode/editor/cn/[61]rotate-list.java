//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 500] 内 
// -100 <= Node.val <= 100 
// 0 <= k <= 2 * 109 
// 
// Related Topics 链表 双指针 
// 👍 644 👎 0


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
//    public ListNode rotateRight(ListNode head, int k) {
//        if (head == null || head.next == null){//空链表，或只有一个节点的情况
//            return head;
//        }
//        int length = 0;//记录链表的长度
//        ListNode temp = head;
//        while (temp != null){
//            length++;
//            temp = temp.next;
//        }
//        k %= length;
//        if (k == 0){
//            return head;
//        }
//
//        temp = head;
//        for (int i = 0; i < length-k-1; i++) {//找到倒数第k+1个节点
//            temp = temp.next;
//        }
//
//        ListNode tail = head;
//        while (tail.next != null){//记录尾节点
//            tail = tail.next;
//        }
//
//        //将链表的最后k个节点拼接到链表的头部，并将链表的尾节点的next置为null
//        ListNode res = temp.next;
//        temp.next = null;
//        tail.next = head;
//        return res;
//    }
public ListNode rotateRight(ListNode head, int k) {
    if (head == null) return head;
    ListNode node = head, tail = head;//记录尾节点
    int n = 0;//记录链表长度
    while (node != null) {
        n++;
        node = node.next;
        if (tail.next != null) tail = tail.next;
    }
    k %= n;
    if (k == 0) return head;
    node = head;
    for(int i = 0; i < n-k-1; i++) {//使node指向倒数第k+1个节点
        node = node.next;
    }
    ListNode newHead = node.next;
    node.next = null;
    tail.next = head;
    return newHead;
}
}
//leetcode submit region end(Prohibit modification and deletion)


