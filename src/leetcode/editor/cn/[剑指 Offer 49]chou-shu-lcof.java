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
// 👍 255 👎 0


package leetcode.editor.cn;


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0]=1;
        int a = 0, b = 0, c = 0;
        for (int i = 1; i < n; i++) {
            int x = dp[a]*2, y = dp[b]*3, z = dp[c]*5;
            int temp = Math.min(Math.min(x, y), z);
            dp[i]=temp;
            if (temp == x) a++;
            if (temp == y) b++;
            if (temp == z) c++;
        }
        return dp[n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


