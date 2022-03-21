//给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。 
//
// 
//
// 示例 1： 
//
// 
//输入: root = [5,3,6,2,4,null,7], k = 9
//输出: true
// 
//
// 示例 2： 
//
// 
//输入: root = [5,3,6,2,4,null,7], k = 28
//输出: false
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [1, 104]. 
// -104 <= Node.val <= 104 
// root 为二叉搜索树 
// -105 <= k <= 105 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉搜索树 哈希表 双指针 二叉树 
// 👍 329 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.lang.annotation.Target;
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
    List<Integer> list;
    public boolean findTarget(TreeNode root, int k) {
        list = new ArrayList<>();
        inorder(root);
        int left = 0, right = list.size()-1;
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k) return true;
            if (sum < k) left++;
            else right--;
        }
        return false;
    }
    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
