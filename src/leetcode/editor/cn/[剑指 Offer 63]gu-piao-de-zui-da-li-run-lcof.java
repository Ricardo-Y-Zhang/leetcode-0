//假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？ 
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
// 
//
// 示例 2: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 10^5 
//
// 
//
// 注意：本题与主站 121 题相同：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-s
//tock/ 
// Related Topics 数组 动态规划 
// 👍 178 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        if (prices.length == 0){
            return res;
        }
        int dp = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp = Math.min(dp, prices[i]);
            res = Math.max(res, prices[i]-dp);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


