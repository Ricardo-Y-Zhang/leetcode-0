//输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。 
//
// 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 
//
// 
//
// 示例 1: 
//
// 
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
//preorder-and-inorder-traversal/ 
// Related Topics 树 数组 哈希表 分治 二叉树 
// 👍 618 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = build( preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
        return root;
    }
    public TreeNode build(int[] preorder, int[] inorder, int left1, int right1, int left2, int right2){
        if (left1 > right1){
            return null;
        }
        int k = 0;//记录中序遍历中当前子树根节点的下标
        for (k = left2; k <= right2; k++){
            if (inorder[k] == preorder[left1]){
                break;
            }
        }
        int length = k-left2;//左子树节点数

        TreeNode root = new TreeNode(preorder[left1]);
        root.left = build(preorder, inorder, left1+1, left1+length, left2, k-1);//递归创建左子树
        root.right = build(preorder, inorder, left1+length+1, right1, k+1, right2);//递归创建右子树
        return root;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


