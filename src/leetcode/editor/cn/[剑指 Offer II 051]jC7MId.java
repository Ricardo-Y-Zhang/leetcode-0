//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
//一定经过根节点。 
//
// 路径和 是路径中各节点值的总和。 
//
// 给定一个二叉树的根节点 root ，返回其 最大路径和，即所有路径上节点值之和的最大值。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围是 [1, 3 * 104] 
// -1000 <= Node.val <= 1000 
// 
//
// 
//
// 注意：本题与主站 124 题相同： https://leetcode-cn.com/problems/binary-tree-maximum-path-s
//um/ 
// Related Topics 树 深度优先搜索 动态规划 二叉树 
// 👍 18 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
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
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }
    public int dfs(TreeNode root){//以 root 为起点的单边路径的最大值
        if (root == null){
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int sum = root.val;//以 root 为左右中点的路径和的最大值
        if (left > 0){
            sum += left;
        }
        if (right > 0){
            sum += right;
        }
        max = Math.max(max, sum);
        return Math.max(left, right)>0?Math.max(left,right)+root.val:root.val;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


