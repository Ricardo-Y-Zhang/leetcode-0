//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 936 👎 0


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
//class Solution {
//    int maxDeepth = 0;
//    public int maxDepth(TreeNode root) {
//        maxDeepth = 0;
//        dps(root, 1);
//        return maxDeepth;
//    }
//
//    public void dps(TreeNode root, int deepth){
//        if (root != null){
//            if (deepth > maxDeepth){
//                maxDeepth = deepth;
//            }
//            dps(root.left, deepth+1);
//            dps(root.right, deepth+1);
//        }
//    }
//}


class Solution {
    public int maxDepth(TreeNode root){
        if (root == null){
            return 0;
        }else{
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


