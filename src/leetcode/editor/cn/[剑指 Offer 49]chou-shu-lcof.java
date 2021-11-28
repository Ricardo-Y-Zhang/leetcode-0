//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。 
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 
// 👍 246 👎 0




//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int nthUglyNumber(int n) {
        long[] dp = new long[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 2*dp[i-1];
            for (int j = i-2; j >= 0; j--) {
                long temp = find(dp[j], dp[i-1]);
                if (temp == -1){
                    break;
                }
                dp[i] = Math.min(temp, dp[i]);
            }
        }
        return (int)dp[n-1];
    }
    public long find(long x, long y){
        if (x*2 > y){
            return 2*x;
        }
        if (3*x > y){
            return 3*x;
        }
        if (5*x > y){
            return 5*x;
        }
        return -1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
