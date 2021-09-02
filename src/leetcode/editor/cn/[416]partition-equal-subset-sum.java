//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
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
// Related Topics 数组 动态规划 
// 👍 927 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;

        for (int temp : nums){
            sum += temp;
        }

        if (sum % 2 != 0){
            return false;
        }

        int target = sum / 2;

        int[][] dp = new int[nums.length][target+1];

        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1;
        }

        if (nums[0] <= target){
            dp[0][nums[0]] = 1;
            if (nums[0] == target){
                return true;
            }
        }

        for (int i = 1; i < nums.length; i++) {
            int t = nums[i];

            if (t <= target){
                dp[i][t] = 1;
                for (int j = 0; j < t; j++) {
                    dp[i][j] = dp[i-1][j];
                }
                for (int j = t; j <= target; j++) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-t]);
                }
            }

            if (dp[i][target] == 1){
                return true;
            }
        }

        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


