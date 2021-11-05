//从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。 
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
//  [9,20],
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
//
// 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-tra
//versal/ 
// Related Topics 树 广度优先搜索 二叉树 
// 👍 156 👎 0


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
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.add(root);
        TreeNode tail = root, tempTail = root;
        List<Integer> temp = new ArrayList<>();
        while (!list.isEmpty()) {
            TreeNode first = list.poll();
            temp.add(first.val);
            if (first.left != null) {
                list.add(first.left);
                tempTail = first.left;
            }
            if (first.right != null) {
                list.add(first.right);
                tempTail = first.right;
            }
            if (first == tail) {//当前节点为该层的最右节点
                tail = tempTail;
                res.add(new ArrayList<>(temp));
                temp.clear();
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


