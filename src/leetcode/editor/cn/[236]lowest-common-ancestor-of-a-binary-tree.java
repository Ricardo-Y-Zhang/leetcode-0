//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 105] 内。 
// -109 <= Node.val <= 109 
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 1237 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//class Solution {
//
//    ArrayList<TreeNode> list0 = new ArrayList<TreeNode>();
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        ArrayList<TreeNode> list1 = new ArrayList<>();
//        ArrayList<TreeNode> list2 = new ArrayList<>();
//
//        DFS(root, p, new ArrayList<TreeNode>());
//        list1.addAll(list0);
//
//        DFS(root, q, new ArrayList<TreeNode>());
//        list2.addAll(list0);
//
//        TreeNode res = new TreeNode();
//        for (int i = 0; i < Math.min(list1.size(), list2.size()); i++) {
//            res = list1.get(i);
//            if (i == list1.size() - 1 || i == list2.size()-1 || list1.get(i+1) != list2.get(i+1)){
//                break;
//            }
//        }
//        return res;
//    }
//
//    public void DFS(TreeNode root, TreeNode target, ArrayList<TreeNode> list){
//        if (root == null){
//            return;
//        }
//        list.add(root);
//        if (root == target){
//            list0.clear();
//            list0.addAll(list);
//            return;
//        }
//        DFS(root.left, target, list);
//        DFS(root.right, target, list);
//        list.remove(list.size()-1);
//    }
//}

class Solution {
    //返回root子树中p,q的最近公共父节点，若不存在，返回null
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;//不存在返回null，本身为最近公共父节点
        TreeNode l = lowestCommonAncestor(root.left, p, q);//在左子树中找
        TreeNode r = lowestCommonAncestor(root.right, p, q);//在右子树中找
        if (l != null && r != null) return root;//p,q在root异侧
        return l == null ? r :  l;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


