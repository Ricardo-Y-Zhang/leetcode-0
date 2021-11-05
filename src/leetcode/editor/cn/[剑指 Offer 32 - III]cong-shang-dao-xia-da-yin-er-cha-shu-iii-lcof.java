//请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。 
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
// Related Topics 树 广度优先搜索 二叉树 
// 👍 152 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        LinkedList<TreeNode> linkedList = new LinkedList<TreeNode>();
        linkedList.add(root);
        LinkedList<Integer> temp = new LinkedList<>();
        TreeNode tail = root, nextTail = root;
        int index = 0;
        while (!linkedList.isEmpty()) {
            TreeNode head = linkedList.poll();
            if (index % 2 == 0){
                temp.addLast(head.val);
            }else{
                temp.addFirst(head.val);
            }
            if (head.left != null) {
                linkedList.add(head.left);
                nextTail = head.left;
            }
            if (head.right != null) {
                linkedList.add(head.right);
                nextTail = head.right;
            }

            if (tail == head) {
                tail = nextTail;
                res.add(new ArrayList<>(temp));
                temp.clear();
                index++;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


