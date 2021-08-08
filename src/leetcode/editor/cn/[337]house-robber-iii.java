//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
//房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。 
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。 
//
// 示例 1: 
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7. 
//
// 示例 2: 
//
// 输入: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 
// 👍 929 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)


import java.util.HashMap;


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
//    HashMap<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
//    public int rob(TreeNode root) {
//        if (root == null){
//            return 0;
//        }
//
//        if (map.containsKey(root)){
//            return map.get(root);
//        }
//
//        int notChoose = rob(root.left) + rob(root.right);
//
//        int lefl = 0, lefr = 0, rigl = 0, rigr = 0;
//        if (root.left != null){
//            lefl = rob(root.left.left);
//            lefr = rob(root.left.right);
//        }
//        if (root.right != null){
//            rigl = rob(root.right.left);
//            rigr = rob(root.right.right);
//        }
//
//        int choose = root.val + lefl + lefr + rigl + rigr;
//
//
//        int max = Math.max(notChoose, choose);
//        map.put(root, max);
//        return max;
//    }
//}

class Solution {

    public int rob (TreeNode root){
        int[] results = get(root);
        return Math.max(results[0], results[1]);
    }

    public int[] get(TreeNode root){
        int[] value = new int[2];
        if (root == null){
            return value;
        }

        int[] left = get(root.left);
        int[] right = get(root.right);

        //打劫，即不打劫其子节点
        int choose = root.val + left[1] + right[1];

        //不打劫，可打劫其子节点，也可不打劫其子节点，取最大值
        int leftMax = Math.max(left[0], left[1]);
        int rightMax = Math.max(right[0], right[1]);

        int notChoose = leftMax + rightMax;

        value[0] = choose;
        value[1] = notChoose;

        return value;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


