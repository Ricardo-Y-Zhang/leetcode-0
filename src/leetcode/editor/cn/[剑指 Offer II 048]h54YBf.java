//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反
//序列化为原始的树结构。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
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
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，也可以采用其他的方法解决这
//个问题。 
// 树中结点数在范围 [0, 104] 内 
// -1000 <= Node.val <= 1000 
// 
//
// 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-b
//inary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 
// 👍 19 👎 0


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
        list.offer(root);
        while (!list.isEmpty()){
            TreeNode node = list.pollFirst();
            if (sb.length() != 0){
                sb.append(",");
            }
            if (node == null){
                sb.append("null");
            }else{
                sb.append(node.val);
                list.offer(node.left);
                list.offer(node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        if (strs.length == 1){
            return null;
        }
        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();//所有节点
        for (int i = 0; i < strs.length; i++) {
            nodes.offer(build(strs[i]));
        }
        TreeNode root = nodes.pollFirst();
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();//层序遍历队列
        list.offer(root);
        while (!nodes.isEmpty()){
            TreeNode node = list.pollFirst();
            node.left = nodes.pollFirst();
            node.right = nodes.pollFirst();
            if (node.left != null){
                list.offer(node.left);
            }
            if (node.right != null){
                list.offer(node.right);
            }
        }
        return root;
    }
    public TreeNode build(String str){
        if (str.equals("null")){
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(str));
        node.left = null;
        node.right = null;
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)


