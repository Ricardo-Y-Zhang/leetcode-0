//给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 814 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;
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
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> list = new ArrayList<>();
        TreeNode pre = null;//记录上一个遍历的节点
        while (!stack.isEmpty()||root!=null) {
            if (root!=null){
                stack.add(root);
                root=root.left;
            }else{
                TreeNode node = stack.peek();
                if (node.right!=null&&pre!=node.right){//存在右孩子节点且未遍历过
                    root = node.right;
                }else{//不存在右孩子节点或已经遍历过右孩子节点
                    root = stack.pop();
                    list.add(root.val);//遍历当前节点
                    pre = root;//更新遍历过的节点
                    root = null;//置为空，防止再次入栈
                }
            }
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


