//给定一个链表数组，每个链表都已经按升序排列。 
//
// 请将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 
//输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 
//输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
//
// 
//
// 注意：本题与主站 23 题相同： https://leetcode-cn.com/problems/merge-k-sorted-lists/ 
// Related Topics 链表 分治 堆（优先队列） 归并排序 
// 👍 24 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)


import java.util.LinkedList;


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
    public ListNode mergeKLists(ListNode[] lists) {
        LinkedList<ListNode> list = new LinkedList<>();
        for (ListNode node : lists){
            list.add(node);
        }
        if (list.size() == 0){
            return null;
        }
        LinkedList<ListNode> temp = new LinkedList<>();
        while (list.size() != 1){
            while (list.size() > 1){
                temp.offer(merge(list.poll(), list.poll()));
            }
            if (!list.isEmpty()){
                temp.offer(list.poll());
            }
            list = new LinkedList<>(temp);
            temp.clear();
        }
        return list.peek();
    }
    public ListNode merge(ListNode node1, ListNode node2){
        ListNode vhead = new ListNode();
        ListNode node = vhead;
        while (node1 != null && node2 != null){
            if (node1.val < node2.val){
                node.next = node1;
                node1 = node1.next;
            }else{
                node.next = node2;
                node2 = node2.next;
            }
            node = node.next;
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


