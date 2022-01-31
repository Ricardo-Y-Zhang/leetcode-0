//给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。 
//
// 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。 
//
// 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。 
//
// 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。 
//
// 
//
// 示例 1： 
//
// 
// 
//
// 
//输入：head = [3,4,1], insertVal = 2
//输出：[3,4,1,2]
//解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后
//，整个列表如上图所示，最后返回节点 3 。
//
//
// 
//
// 示例 2： 
//
// 
//输入：head = [], insertVal = 1
//输出：[1]
//解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
// 
//
// 示例 3： 
//
// 
//输入：head = [1], insertVal = 0
//输出：[1,0]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= Number of Nodes <= 5 * 10^4 
// -10^6 <= Node.val <= 10^6 
// -10^6 <= insertVal <= 10^6 
// 
//
// 
//
// 注意：本题与主站 708 题相同： https://leetcode-cn.com/problems/insert-into-a-sorted-circu
//lar-linked-list/ 
// Related Topics 链表 
// 👍 21 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        if (head == null){
            head = new Node(insertVal, null);
            head.next = head;
            return head;
        }
        if (head.next == head){
            Node node = new Node(insertVal, head);
            head.next = node;
            return head;
        }
        Node min = head.next, minPre = head, node = head.next, pre = head;
        int max = head.val;
        while (node != head){
            if (node.val < min.val){
                min = node;
                minPre = pre;
            }
            max = Math.max(max, node.val);
            node = node.next;
            pre = pre.next;
        }
        if (node.val <= min.val){
            min = node;
            minPre = pre;
        }
        if (insertVal > min.val && insertVal < max){
            while (min.val < insertVal){
                min = min.next;
                minPre = minPre.next;
            }
        }
        node = new Node(insertVal, min);
        minPre.next = node;
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


