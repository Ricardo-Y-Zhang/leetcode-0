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
// 👍 251 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)




import java.util.LinkedList;

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
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.offerLast(root);
        while (!list.isEmpty()){
            if (sb.length() != 0){
                sb.append(",");
            }
            TreeNode first = list.pollFirst();
            if (first==null){
                sb.append("null");
            }else{
                sb.append(String.valueOf(first.val));
                list.offerLast(first.left);
                list.offerLast(first.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        if (nodes.length == 1){
            return null;
        }
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        list.add(root);
        int i = 1;
        while (!list.isEmpty()){
            TreeNode first = list.pollFirst();
            if (first != null){
                if ("null".equals(nodes[i])){
                    first.left = null;
                    i++;
                } else {
                    first.left = new TreeNode(Integer.parseInt(nodes[i++]));
                }
                if ("null".equals(nodes[i])){
                    first.right = null;
                    i++;
                }else{
                    first.right = new TreeNode(Integer.parseInt(nodes[i++]));
                }
                if (first.left != null){
                    list.offerLast(first.left);
                }
                if (first.right != null){
                    list.offerLast(first.right);
                }
            }
        }

        return root;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)


