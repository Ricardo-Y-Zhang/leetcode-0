//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
//
// 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/ 
// Related Topics 树 深度优先搜索 回溯 二叉树 
// 👍 267 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

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
    List<List<Integer>> res;
    ArrayList<Integer> temp;
    int target;
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        this.target=target;
        dfs(root, 0);
        return res;
    }
    public void dfs(TreeNode root, int sum){
        if (root == null){
            return;
        }else{
            temp.add(root.val);
            sum+=root.val;
            if (root.left == null && root.right == null && sum == target){
                res.add(new ArrayList<>(temp));
            }
            if (root.left != null){
                dfs(root.left, sum);
            }
            if (root.right != null){
                dfs(root.right, sum);
            }
            temp.remove(temp.size()-1);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


