//给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。 
//
// 
//
// 示例 1 : 
//
// 
//输入:nums = [1,1,1], k = 2
//输出: 2
//解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
// 
//
// 示例 2 : 
//
// 
//输入:nums = [1,2,3], k = 3
//输出: 2
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 2 * 104 
// -1000 <= nums[i] <= 1000 
// 
// -107 <= k <= 107 
// 
// 
//
// 
//
// 注意：本题与主站 560 题相同： https://leetcode-cn.com/problems/subarray-sum-equals-k/ 
// Related Topics 数组 哈希表 前缀和 
// 👍 30 👎 0


package leetcode.editor.cn;

import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarraySum(int[] nums, int k) {
        //前缀和
        HashMap<Integer, Integer> map = new HashMap<>();
        int prefix = 0, res = 0;
        map.put(prefix, 1);
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            //找到前缀和为 prefix-k 的个数
            res += map.getOrDefault(prefix-k, 0);
            map.put(prefix, map.getOrDefault(prefix, 0)+1);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


