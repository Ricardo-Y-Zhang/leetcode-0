//请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。 
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
// Related Topics 树 广度优先搜索 二叉树 
// 👍 156 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if (root == null){
            return res;
        }
        List<Integer> temp = new ArrayList<Integer>();
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.offerLast(root);
        TreeNode last = root;
        int deepth = 0;
        while (!list.isEmpty()){
            TreeNode first = list.pollFirst();
            temp.add(first.val);
            if (first.left != null){
                list.offerLast(first.left);
            }
            if (first.right != null){
                list.offerLast(first.right);
            }
            if (first == last){
                if (deepth%2 == 0){
                    res.add(new ArrayList<>(temp));
                }else{
                    ArrayList<Integer> rever = new ArrayList<>();
                    for (int i = 0; i < temp.size(); i++) {
                        rever.add(temp.get(temp.size()-i-1));
                    }
                    res.add(new ArrayList<>(rever));
                }
                temp.clear();
                deepth++;
                last = list.peekLast();
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


