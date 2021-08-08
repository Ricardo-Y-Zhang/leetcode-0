//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。 
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
// 
//
// 示例 2： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,1000] 
// -109 <= Node.val <= 109 
// -1000 <= targetSum <= 1000 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 937 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;

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
    int res;
    public int pathSum(TreeNode root, int targetSum) {
        res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        getSum(root, targetSum, 0, map);
        return res;
    }

    public void getSum(TreeNode root, int targetSum, int tempSum, HashMap<Integer, Integer> map){
        if (root == null){
            return;
        }

        tempSum += root.val;

        int need = tempSum - targetSum;

        res += map.getOrDefault(need, 0);

        map.put(tempSum, map.getOrDefault(tempSum, 0)+1);

        getSum(root.left, targetSum, tempSum, map);
        getSum(root.right, targetSum, tempSum, map);
        map.put(tempSum, map.get(tempSum)-1);
    }

}
//leetcode submit region end(Prohibit modification and deletion)


