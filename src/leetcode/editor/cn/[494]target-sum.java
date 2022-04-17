//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
// Related Topics 数组 动态规划 回溯 
// 👍 892 👎 0


package leetcode.editor.cn;



//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //背包问题
    public int findTargetSumWays(int[] nums, int target) {
        int N = 2010;
        int n = nums.length;
        int[][] dp = new int[n+1][N];//dp[i][j]表示nums中[0,i)元素组成 j-1000 的表达式的数目
        dp[0][1000] = 1;
        for (int i = 1; i < n+1; i++) {
            for (int j = 0; j < N; j++) {
                if (j-nums[i-1]>=0){
                    dp[i][j] += dp[i-1][j-nums[i-1]];//nums中[0,i-1)元素组成 j-1000-nums[i-1]
                }
                if (j+nums[i-1] < N){
                    dp[i][j] += dp[i-1][j+nums[i-1]];//nums中[0,i-1)元素组成 j-1000+nums[i-1]
                }

            }
        }
        return dp[n][target+1000];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


