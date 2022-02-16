//给定一个非空的正整数数组 nums ，请判断能否将这些数字分成元素和相等的两部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：nums 可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：nums 不可以分为和相等的两部分
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
//
// 
//
// 注意：本题与主站 416 题相同： https://leetcode-cn.com/problems/partition-equal-subset-sum
/// 
// Related Topics 数学 字符串 模拟 
// 👍 22 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPartition(int[] nums) {
        int length = nums.length;
        int target = 0;
        for (int temp : nums){
            target += temp;
        }
        if (target % 2 == 1){//奇数，不满足条件
            return false;
        }
        target /= 2;
        boolean[][] dp = new boolean[length+1][target+1];
        dp[0][0] = true;
        for (int i = 1; i < length+1; i++) {
            for (int j = 0; j < target+1; j++) {
                dp[i][j] = dp[i-1][j] || ((j-nums[i-1])>=0&&dp[i-1][j-nums[i-1]]);
            }
        }
        return dp[length][target];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


