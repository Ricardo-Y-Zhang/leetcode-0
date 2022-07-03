//给定一个字符串 s，返回 s 中不同的非空「回文子序列」个数 。 
//
// 通过从 s 中删除 0 个或多个字符来获得子序列。 
//
// 如果一个字符序列与它反转后的字符序列一致，那么它是「回文字符序列」。 
//
// 如果有某个 i , 满足 ai != bi ，则两个序列 a1, a2, ... 和 b1, b2, ... 不同。 
//
// 注意： 
//
// 
// 结果可能很大，你需要对 10⁹ + 7 取模 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = 'bccb'
//输出：6
//解释：6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
//注意：'bcb' 虽然出现两次但仅计数一次。
// 
//
// 示例 2： 
//
// 
//输入：s = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
//输出：104860361
//解释：共有 3104860382 个不同的非空回文子序列，104860361 对 10⁹ + 7 取模后的值。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s[i] 仅包含 'a', 'b', 'c' 或 'd' 
// 
// Related Topics 字符串 动态规划 👍 206 👎 0


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countPalindromicSubsequences(String s) {
        int mod = (int)1e9+7;
        int n = s.length();
        char[] chs = s.toCharArray();
        long[][] dp = new long[n][n];//dp[i][j]表示s[i,j]中回文串个数
        for (int i = 0; i < n; i++) {//单个字符构成回文串
            dp[i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i+len <= n; i++) {//左边界
                int j = i+len-1;//右边界
                if (chs[i] == chs[j]) {
                    int l = i+1, r = j-1;
                    while (chs[l] != chs[i]) l++;
                    while (chs[r] != chs[i]) r--;
                    if (l > r) {//[i+1, j-1]中不存在与 chs[i]相同的字符
                        dp[i][j] = 2*dp[i+1][j-1]+2;
                    }else if (l == r) {//存在一个相同的字符
                        dp[i][j] = 2*dp[i+1][j-1]+1;
                    }else {//存在两个或以上
                        dp[i][j] = 2*dp[i+1][j-1] -dp[l+1][r-1];
                    }
                }else{
                    dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1];//由于取mod，可能为负数
                }
                dp[i][j] += mod;
                dp[i][j] %= mod;
            }
        }
        return (int)dp[0][n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
