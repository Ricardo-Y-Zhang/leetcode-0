//给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。 
//
// 
//
// 示例1： 
//
// 
//输入: root = [1,3,2,5,3,null,9]
//输出: [1,3,9]
//解释:
//          1
//         / \
//        3   2
//       / \   \  
//      5   3   9 
// 
//
// 示例2： 
//
// 
//输入: root = [1,2,3]
//输出: [1,3]
//解释:
//          1
//         / \
//        2   3
// 
//
// 示例3： 
//
// 
//输入: root = [1]
//输出: [1]
// 
//
// 示例4： 
//
// 
//输入: root = [1,null,2]
//输出: [1,2]
//解释:      
//           1 
//            \
//             2     
// 
//
// 示例5： 
//
// 
//输入: root = []
//输出: []
// 
//
// 
//
// 提示： 
//
// 
// 二叉树的节点个数的范围是 [0,104] 
// -231 <= Node.val <= 231 - 1 
// 
//
// 
//
// 注意：本题与主站 515 题相同： https://leetcode-cn.com/problems/find-largest-value-in-each
//-tree-row/ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 11 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        TreeNode tail = root;
        int max = Integer.MIN_VALUE;
        while (!list.isEmpty()){
            TreeNode node = list.pollFirst();
            max = Math.max(max, node.val);
            if (node.left != null){
                list.add(node.left);
            }
            if (node.right != null){
                list.add(node.right);
            }

            //当前节点是该层最右节点
            if (node == tail){
                res.add(max);
                tail = list.peekLast();//更新下一层的最右节点
                max = Integer.MIN_VALUE;//重置最大值
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


