//给定一个未排序的整数数组，找到最长递增子序列的个数。 
//
// 示例 1: 
//
// 
//输入: [1,3,5,4,7]
//输出: 2
//解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
// 
//
// 示例 2: 
//
// 
//输入: [2,2,2,2,2]
//输出: 5
//解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
// 
//
// 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。 
// Related Topics 树状数组 线段树 数组 动态规划 
// 👍 418 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findNumberOfLIS(int[] nums) {

        int[] dp = new int[nums.length];

        int[] num = new int[nums.length];

        Arrays.fill(dp, 1);

        Arrays.fill(num, 1);

        int maxLen = 0, res = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]){
                    int temp = dp[j] + 1;
                    if (temp == dp[i]){
                        num[i] += num[j];
                    }else if (temp > dp[i]){
                        num[i] = num[j];
                        dp[i] = temp;
                    }
                }
            }
            if (dp[i] > maxLen){
                maxLen = dp[i];
            }
        }

        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == maxLen){
                res += num[i];
            }
        }


        for (int i = 0; i < dp.length; i++) {
            System.out.println("dp[i] = " + dp[i]);
        }

        for (int i = 0; i < num.length; i++) {
            System.out.println("num[i] = " + num[i]);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
