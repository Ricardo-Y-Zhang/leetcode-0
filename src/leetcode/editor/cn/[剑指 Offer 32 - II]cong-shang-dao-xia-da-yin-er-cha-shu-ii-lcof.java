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
// 👍 160 👎 0


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
        if (root == null){
            return new ArrayList<List<Integer>>();
        }
        ArrayList<List<Integer>> res = new ArrayList<>();//记录结果
        ArrayList<Integer> temp = new ArrayList<>();//记录当前层的节点值
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();//队列
        list.offerLast(root);
        TreeNode last = root;
        while (!list.isEmpty()){
            TreeNode first = list.pollFirst();
            temp.add(first.val);
            if (first.left != null){
                list.offerLast(first.left);
            }
            if (first.right != null){
                list.offerLast(first.right);
            }
            if (first == last){//当前节点为该层的最后一个节点
                res.add(new ArrayList<>(temp));
                temp.clear();
                last = list.peekLast();//更新last节点
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


