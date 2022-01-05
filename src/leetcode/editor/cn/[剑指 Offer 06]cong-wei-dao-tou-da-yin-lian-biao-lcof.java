//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。 
//
// 
//
// 示例 1： 
//
// 输入：head = [1,3,2]
//输出：[2,3,1] 
//
// 
//
// 限制： 
//
// 0 <= 链表长度 <= 10000 
// Related Topics 栈 递归 链表 双指针 
// 👍 210 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        if (head == null){
            return new int[0];
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        while (head != null){
            linkedList.offerLast(head.val);
            head = head.next;
        }
        int[] res = new int[linkedList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = linkedList.pollLast();
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


