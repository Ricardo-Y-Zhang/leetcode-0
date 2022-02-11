//给定一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：nums = [3,10,5,25,2,8]
//输出：28
//解释：最大运算结果是 5 XOR 25 = 28. 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [2,4]
//输出：6
// 
//
// 示例 4： 
//
// 
//输入：nums = [8,10,2]
//输出：10
// 
//
// 示例 5： 
//
// 
//输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
//输出：127
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// 0 <= nums[i] <= 231 - 1 
// 
// 
// 
//
// 
//
// 进阶：你可以在 O(n) 的时间解决这个问题吗？ 
//
// 
//
// 注意：本题与主站 421 题相同： https://leetcode-cn.com/problems/maximum-xor-of-two-numbers
//-in-an-array/ 
// Related Topics 位运算 字典树 数组 哈希表 
// 👍 22 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMaximumXOR(int[] nums) {
        Trie tree = new Trie();
        int max = Integer.MIN_VALUE;
        for (int num : nums){
            tree.insert(num);
            max = Math.max(max, tree.searchMaxXOR(num));
        }
        return max;
    }

    class Trie{
        class TreeNode{
            TreeNode[] next;
            public TreeNode(){
                next = new TreeNode[2];
            }
        }
        TreeNode root;
        Trie(){
            root = new TreeNode();
        }
        public void insert(int num){
            TreeNode node = root;
            for (int i = 30; i >= 0; i--){
                int d = num >> i & 1;
                if (node.next[d] == null){
                    node.next[d] = new TreeNode();
                }
                node = node.next[d];
            }
        }

        public int searchMaxXOR(int num){
            TreeNode node = root;
            int xorNum = 0;
            for (int i = 30; i >= 0; i--){
                int d = num >> i & 1;
                int other = (d == 1 ? 0 : 1);
                if (node.next[other] == null){//相反位为空
                    node = node.next[d];
                    xorNum = (xorNum << 1) + d;
                }else{
                    node = node.next[other];
                    xorNum = (xorNum << 1) + other;
                }
            }
            return xorNum ^ num;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


