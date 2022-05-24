//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 
// 👍 1150 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;

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
    /*
    public boolean isValidBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        inorder(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)){
                return false;
            }
        }
        return true;
    }
    public void inorder(TreeNode root, ArrayList<Integer> list){
        if (root != null){
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }

     */

    ArrayList<Integer> list = new ArrayList<>();
    public boolean isValidBST(TreeNode root) {
        dfs(root);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i+1)) return false;
        }
        return true;
    }
    public void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }
}


//class Solution{
//    public boolean isValidBST(TreeNode root){
//        return judge(root, Long.MAX_VALUE, Long.MIN_VALUE);
//    }
//    public boolean judge(TreeNode root, long upper, long lower){
//        if (root == null){
//            return true;
//        }
//        if (root.val <= lower || root.val >= upper){
//            return false;
//        }else{
//            return judge(root.left, root.val, lower) && judge(root.right, upper, root.val);
//        }
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)


