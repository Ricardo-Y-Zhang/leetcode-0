//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 1475 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Stack;

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
//    public boolean isSymmetric(TreeNode root) {
//        return judge(root, root);
//    }
//
//    public boolean judge(TreeNode root1, TreeNode root2){
//        if (root1 != null && root2 != null){
//            if (root1.val == root2.val){
//                return judge(root1.left, root2.right) && judge(root1.right, root2.left);
//            }else{
//                return false;
//            }
//        }else if (root1 == null && root2 == null){
//            return true;
//        }else{
//            return false;
//        }
//    }
//}

class Solution{
    public boolean isSymmetric(TreeNode root){
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        TreeNode root1 = root, root2 = root;
        while (root1 != null || root2 != null || !stack1.isEmpty() || !stack2.isEmpty()){
            if (root1 != null && root2 != null){
                stack1.add(root1);
                stack2.add(root2);
                if (root1.val != root2.val){
                    return false;
                }
                root1 = root1.left;
                root2 = root2.right;
            }else if(root1 == null && root2 == null){
                root1 = stack1.pop();
                root2 = stack2.pop();
                root1 = root1.right;
                root2 = root2.left;
            }else{
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


