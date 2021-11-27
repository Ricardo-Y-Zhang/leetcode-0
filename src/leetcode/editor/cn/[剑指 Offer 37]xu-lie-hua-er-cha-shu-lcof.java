//请实现两个函数，分别用来序列化和反序列化二叉树。 
//
// 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字
//符串反序列化为原始的树结构。 
//
// 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方
//法解决这个问题。 
//
// 
//
// 示例： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-b
//inary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 
// 👍 247 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        String res = "";
        while (!q.isEmpty()){
            TreeNode first = q.poll();
            if (!"".equals(res)){
                res += ",";
            }
            if (first == null){
                res += "null";
            }else{
                res += first.val;
                q.add(first.left);
                q.add(first.right);
            }
        }
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].equals("null")){
                list.add(null);
            }else{
                list.add(new TreeNode(Integer.parseInt(nodes[i])));
            }
        }
        ArrayList<TreeNode> temp = new ArrayList<TreeNode>();
        TreeNode root = list.get(0);
        list.remove(0);
        temp.add(root);
        while (!list.isEmpty()){
            TreeNode left = list.get(0);
            list.remove(0);
            TreeNode right = list.get(0);
            list.remove(0);
            TreeNode node = temp.get(0);
            temp.remove(0);
            node.left = left;
            node.right = right;
            if (left != null){
                temp.add(left);
            }
            if (right != null){
                temp.add(right);
            }
        }
        return root;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)


