//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。 
//
// 
//
// 示例 1： 
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
// 树中结点数在范围 [0, 104] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 
// 👍 608 👎 0


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
        String str = "";
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        str += nodeToString(root);
        while (!list.isEmpty()){
            TreeNode first = list.pollFirst();
            if (first != null){
                list.add(first.left);
                str += nodeToString(first.left);
                list.add(first.right);
                str += nodeToString(first.right);
            }
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        boolean flag = false;
        LinkedList<TreeNode> list = new LinkedList<>();
        TreeNode root = null;
        String state = data.substring(0, 1);
        data = data.substring(1);
        if ("0".equals(state)){
            root = null;
        }else{
            String temp = data.substring(0, 1);//正负
            data = data.substring(1);
            int digit = Integer.parseInt(data.substring(0, 1));
            data = data.substring(1);
            int val = Integer.parseInt(data.substring(0, digit));
            data = data.substring(digit);
            if ("0".equals(temp)){
                val *= -1;
            }
            root = new TreeNode(val);
        }
        list.add(root);
        boolean flag1 = false;
        boolean flag2 = false;

        TreeNode first = list.pollFirst();
        while (!data.isEmpty()){

            state = data.substring(0, 1);
            data = data.substring(1);
            TreeNode node = null;
            if ("0".equals(state)){
                node = null;
            }else{
                String temp = data.substring(0, 1);//正负
                data = data.substring(1);
                int digit = Integer.parseInt(data.substring(0, 1));
                data = data.substring(1);
                int val = Integer.parseInt(data.substring(0, digit));
                data = data.substring(digit);
                if ("0".equals(temp)){
                    val *= -1;
                }
                node = new TreeNode(val);
                list.add(node);
            }
            if (flag1 == false){
                first.left = node;
                flag1 = true;
            }else{
                first.right = node;
                flag2 = true;
            }
            if (flag1 == true && flag2 == true && !data.isEmpty()){
                first = list.pollFirst();
                flag1 = false;
                flag2 = false;
            }
        }
        return root;
    }

    public String nodeToString(TreeNode root){
        var str = "";
        if (root == null){
            str += 0;
        }else{
            str += 1;
            int val = root.val;
            if (val < 0){
                str += 0;
            }else{
                str += 1;
            }
            val = Math.abs(val);
            int digit = 0;
            if (val == 0){
                digit++;
            }
            int temp = val;
            while (temp != 0){
                temp /= 10;
                digit++;
            }
            str += digit;
            str += val;
        }
        return str;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)


