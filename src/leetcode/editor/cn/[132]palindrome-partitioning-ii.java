//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。 
//
// 返回符合要求的 最少分割次数 。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：1
//解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：s = "ab"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2000 
// s 仅由小写英文字母组成 
// 
// 
// 
// Related Topics 字符串 动态规划 
// 👍 549 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = n-1; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i+1; j < n; j++) {
                if (j == i+1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1];
                }
            }
        }
        int[] count = new int[n+1];//[0,i-1]的最少回文串组成
        Arrays.fill(count, n);
        count[0] = 0;
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j <= i; j++) {
                if (dp[j-1][i-1] == true) {
                    count[i] = Math.min(count[j-1] + 1, count[i]);
                }
            }
        }
        return count[n]-1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
