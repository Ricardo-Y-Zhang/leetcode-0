//给定一个字符串 s，请将 s 分割成一些子串，使每个子串都是回文串。 
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
//
// 
//
// 注意：本题与主站 132 题相同： https://leetcode-cn.com/problems/palindrome-partitioning-ii
/// 
// Related Topics 字符串 动态规划 
// 👍 13 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];//[i,j]是否为回文串
        for (int i = n-1; i >= 0; i--){
            dp[i][i] = true;//字符本身是回文串
            for (int j = i+1; j < n; j++) {
                if (j == i+1){
                    dp[i][j] = s.charAt(i)==s.charAt(j);
                }else{
                    dp[i][j] = dp[i+1][j-1] && s.charAt(i)==s.charAt(j);
                }
            }
        }
        int[] count = new int[n];//[0,i]的最小分割数
        Arrays.fill(count, n-1);
        for (int i = 0; i < n; i++) {
            if (dp[0][i] == true){
                count[i] = 0;
            }else{
                for (int j = 0; j < i; j++) {
                    if (dp[j+1][i] == true){
                        count[i] = Math.min(count[i],count[j]+1);
                    }
                }
            }
        }
        return count[n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


