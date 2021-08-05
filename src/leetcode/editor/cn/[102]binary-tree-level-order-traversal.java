//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层序遍历结果： 
//
// 
//[
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 二叉树 
// 👍 955 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();

        if (root == null){
            return list;
        }

        TreeNode last = root;//记录每一层的最后一个节点

        LinkedList<TreeNode> list2 = new LinkedList<TreeNode>();
        list2.add(root);

        while (!list2.isEmpty()){
            TreeNode  now = list2.poll();
            list1.add(now.val);

            if (now.left != null){
                list2.add(now.left);
            }
            if (now.right != null){
                list2.add(now.right);
            }

            if (now == last){
                ArrayList<Integer> tempList = new ArrayList<>(list1);
                list.add(tempList);
                list1.clear();
                if (!list2.isEmpty()){
                    last = list2.getLast();
                }
            }
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


