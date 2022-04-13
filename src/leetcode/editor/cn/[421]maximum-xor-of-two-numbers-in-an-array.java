//给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。 
//
// 进阶：你可以在 O(n) 的时间解决这个问题吗？ 
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
// Related Topics 位运算 字典树 数组 哈希表 
// 👍 432 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class Trie{
        Trie[] next;
        Trie(){
            next = new Trie[2];
        }
    }
    Trie root = new Trie();
    void insert(int num) {
        Trie node = root;
        for (int i = 31; i >= 0; i--) {//从高位向低位存储
            int bit = (num>>i) & 1;
            if (node.next[bit]==null) node.next[bit] = new Trie();
            node = node.next[bit];
        }
    }
    int findMaximumXOR(int num) {//找到字典树中与num异或的最大值
        Trie node = root;
        int ans = 0;
        for (int i = 31; i >= 0; i--) {//从高位向低位找
            int bit = (num>>i) & 1;
            if (node.next[1-bit] != null) {
                node = node.next[1-bit];
                ans += 1<<i;
            }else{
                node = node.next[bit];
            }
        }
        return ans;
    }

    public int findMaximumXOR(int[] nums) {
        for (int num : nums) {
            insert(num);
        }
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, findMaximumXOR(num));//对于每个数找到其最大异或值
        }
        return max;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


