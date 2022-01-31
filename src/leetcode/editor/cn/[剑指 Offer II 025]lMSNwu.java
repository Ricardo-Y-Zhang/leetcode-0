//给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。 
//
// 可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 示例1： 
//
// 
//
// 
//输入：l1 = [7,2,4,3], l2 = [5,6,4]
//输出：[7,8,0,7]
// 
//
// 示例2： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[8,0,7]
// 
//
// 示例3： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 100] 
// 0 <= node.val <= 9 
// 输入数据保证链表代表的数字无前导 0 
// 
//
// 
//
// 进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。 
//
// 
//
// 注意：本题与主站 445 题相同：https://leetcode-cn.com/problems/add-two-numbers-ii/ 
// Related Topics 栈 链表 数学 
// 👍 20 👎 0


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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n = 0, m = 0;
        ListNode node = l1;
        while (node != null){
            node = node.next;
            n++;
        }
        node = l2;
        while (node != null){
            node = node.next;
            m++;
        }
        int diff = Math.abs(n-m);
        ListNode  lnode , snode;
        if (n >= m){
            lnode = l1;
            snode = l2;
        }else{
            lnode = l2;
            snode = l1;
        }

        ListNode vhead = new ListNode();
        vhead.next = null;
        ListNode node1 = lnode, node2 = snode;
        for (int i = 0; i < diff; i++) {
            ListNode nnode = new ListNode(node1.val);
            nnode.next = vhead.next;
            vhead.next = nnode;
            node1 = node1.next;
        }
        while (node1 != null){
            ListNode nnode = new ListNode(node1.val+node2.val);
            nnode.next = vhead.next;
            vhead.next = nnode;
            node1 = node1.next;
            node2 = node2.next;
        }

        ListNode vvhead = new ListNode();
        vvhead.next = null;
        node = vhead.next;
        int plus = 0;
        while (node != null){
            int sum = node.val;
            node.val = (sum+plus)%10;
            plus = (sum+plus)/10;
            ListNode temp = node;
            node = node.next;
            temp.next = vvhead.next;
            vvhead.next = temp;
        }
        if (plus > 0){
            ListNode temp = new ListNode(plus);
            temp.next = vvhead.next;
            vvhead.next = temp;
        }
        return vvhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


