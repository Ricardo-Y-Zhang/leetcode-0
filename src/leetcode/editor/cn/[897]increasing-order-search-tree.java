//给你一棵二叉搜索树的 root ，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右
//子节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
//输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
// 
//
// 示例 2： 
//
// 
//输入：root = [5,1,7]
//输出：[1,null,5,null,7]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的取值范围是 [1, 100] 
// 0 <= Node.val <= 1000 
// 
// Related Topics 栈 树 深度优先搜索 二叉搜索树 二叉树 
// 👍 267 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
    TreeNode vroot, pre;//虚拟根节点和前序节点
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        vroot = new TreeNode(-1);
        pre = vroot;
        inorder(root);
        return vroot.right;
    }
    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        //改变节点指向
        pre.right = root;//前序节点right指向root
        root.left = null;//左指针置空
        pre = root;//更新前序节点
        inorder(root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


